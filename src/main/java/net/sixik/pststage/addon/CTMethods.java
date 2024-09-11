package net.sixik.pststage.addon;


import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.resources.ResourceLocation;
import net.sixik.pststage.addon.actions.SkillTreeSkillStageAction;
import net.sixik.pststage.addon.actions.SkillTreeStageAction;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.pststage.PSTStage")
public class CTMethods {

    @ZenCodeType.Method
    public static void applySkillTreeStage(String stageID, ResourceLocation[] skillTreeID) {
        CraftTweakerAPI.apply(new SkillTreeStageAction(stageID, skillTreeID));
    }

    @ZenCodeType.Method
    public static void applySkillStage(String stageID, ResourceLocation[] skillsID){
        CraftTweakerAPI.apply(new SkillTreeSkillStageAction(stageID, skillsID));
    }
}
