package net.sixik.pststage.addon.actions;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.sixik.pststage.ReloadContainer;
import net.sixik.pststage.network.SendStagesS2C;
import net.sixik.pststage.type.SkillTreeStage;

import java.util.ArrayList;
import java.util.List;

public class SkillTreeStageAction implements IRuntimeAction {
    public final List<ResourceLocation> skills;
    public final String stage;

    public SkillTreeStageAction(String stage, ResourceLocation[] skills) {
        this.skills = new ArrayList<>(List.of(skills));
        this.stage = stage;
    }

    @Override
    public void apply() {
        ReloadContainer.INSTANCE.skillTreeStage = new SkillTreeStage(stage, skills);
        if(ServerLifecycleHooks.getCurrentServer() != null){
            new SendStagesS2C(ReloadContainer.INSTANCE.serializeNBT()).sendToAll(ServerLifecycleHooks.getCurrentServer());
        }
    }

    @Override
    public String describe() {
        return "Restricted passive skill tree " + skills.stream().map(ResourceLocation::toString).toList() + " on " + stage + ".stage";
    }

    @Override
    public String systemName() {
        return "PSTStage";
    }
}
