package net.sixik.pststage.addon.actions;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.sixik.pststage.ReloadContainer;
import net.sixik.pststage.network.SendStagesS2C;
import net.sixik.pststage.type.SkillTreeSkillStage;

import java.util.ArrayList;
import java.util.List;

public class SkillTreeSkillStageAction implements IRuntimeAction {
    public final List<ResourceLocation> skills;
    public final String stage;

    public SkillTreeSkillStageAction(String stage, ResourceLocation[] skills) {
        this.skills = new ArrayList<>(List.of(skills));
        this.stage = stage;
    }

    @Override
    public void apply() {
        ReloadContainer.INSTANCE.skillTasks.add(new SkillTreeSkillStage(stage, skills));
        if(ServerLifecycleHooks.getCurrentServer() != null){
            new SendStagesS2C(ReloadContainer.INSTANCE.serializeNBT()).sendToAll(ServerLifecycleHooks.getCurrentServer());
        }
    }

    @Override
    public String describe() {
        return "Restricted skills " + skills.stream().map(ResourceLocation::toString).toList() + " on " + stage + ".stage";
    }

    @Override
    public String systemName() {
        return "PSTStage";
    }
}
