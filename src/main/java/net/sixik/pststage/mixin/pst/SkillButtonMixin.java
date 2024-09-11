package net.sixik.pststage.mixin.pst;

import daripher.skilltree.client.widget.skill.SkillButton;
import daripher.skilltree.skill.PassiveSkill;
import daripher.skilltree.skill.PassiveSkillTree;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.sixik.pststage.PSTStage;
import net.sixik.pststage.type.SkillTreeSkillStage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = SkillButton.class, remap = false)
public class SkillButtonMixin {

    @Shadow @Final public PassiveSkill skill;

    @Inject(method = "getSkillTooltip", at = @At("RETURN"))
    public void sdm$getTooltip(PassiveSkillTree skillTree, CallbackInfoReturnable<List<MutableComponent>> cir){
        if(!PSTStage.CLIENT.skillTasks.isEmpty()){
            for (SkillTreeSkillStage skillTask : PSTStage.CLIENT.skillTasks) {
                if(skillTask.skills.contains(skill.getId()) && !GameStageHelper.hasStage(Minecraft.getInstance().player, skillTask.stage)){
                    cir.getReturnValue().add(Component.translatable("sdm.pststage.stageunlock", skillTask.stage).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @Inject(method = "setCanLearn", at = @At("HEAD"), cancellable = true)
    public void sdm$setCanLearn(CallbackInfo ci){
        if(!PSTStage.CLIENT.skillTasks.isEmpty()){
            for (SkillTreeSkillStage skillTask : PSTStage.CLIENT.skillTasks) {
                if(skillTask.skills.contains(skill.getId()) && !GameStageHelper.hasStage(Minecraft.getInstance().player, skillTask.stage)){
                    ci.cancel();
                }
            }
        }
    }
}
