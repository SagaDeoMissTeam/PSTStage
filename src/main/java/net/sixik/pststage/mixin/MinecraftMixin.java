package net.sixik.pststage.mixin;

import daripher.skilltree.client.screen.SkillTreeScreen;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.sixik.pststage.PSTStage;
import net.sixik.pststage.ReloadContainer;
import net.sixik.pststage.mixin.pst.SkillTreeScreenAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    public void sdm$setScreen(Screen screen, CallbackInfo ci){
        if(screen instanceof SkillTreeScreen skillTreeScreen){
            if(!PSTStage.CLIENT.skillTreeStage.isEmpty()) {
                if(PSTStage.CLIENT.skillTreeStage.treesID.contains(((SkillTreeScreenAccessor)skillTreeScreen).getSkillTree().getId()) &&
                        !GameStageHelper.hasStage(Minecraft.getInstance().player, PSTStage.CLIENT.skillTreeStage.stage)){
                    ci.cancel();
                }
            }
        }
    }
}
