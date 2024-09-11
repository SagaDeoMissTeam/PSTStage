package net.sixik.pststage.mixin.pst;

import daripher.skilltree.client.screen.SkillTreeScreen;
import daripher.skilltree.skill.PassiveSkillTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = SkillTreeScreen.class, remap = false)
public interface SkillTreeScreenAccessor {

    @Accessor("skillTree")
    PassiveSkillTree getSkillTree();
}
