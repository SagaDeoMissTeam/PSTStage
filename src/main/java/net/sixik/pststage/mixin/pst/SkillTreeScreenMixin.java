package net.sixik.pststage.mixin.pst;

import daripher.skilltree.client.screen.SkillTreeScreen;
import daripher.skilltree.skill.PassiveSkill;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.sixik.pststage.PSTStage;
import net.sixik.pststage.type.SkillTreeSkillStage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SkillTreeScreen.class, remap = false)
public class SkillTreeScreenMixin {

    @Inject(method = "canLearnSkill", at = @At("HEAD"), cancellable = true)
    public void sdm$canLearnSkill(PassiveSkill skill, CallbackInfoReturnable<Boolean> cir){
        if(!PSTStage.CLIENT.skillTasks.isEmpty()){
            for (SkillTreeSkillStage skillTask : PSTStage.CLIENT.skillTasks) {
                if(skillTask.skills.contains(skill.getId()) && !GameStageHelper.hasStage(Minecraft.getInstance().player, skillTask.stage)){
                    cir.setReturnValue(false);
                    return;
                }
            }
        }

    }
}
