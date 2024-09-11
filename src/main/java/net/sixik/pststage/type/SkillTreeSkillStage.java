package net.sixik.pststage.type;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class SkillTreeSkillStage implements INBTSerializable<CompoundTag> {
    public static SkillTreeSkillStage EMPTY = new SkillTreeSkillStage("", new ArrayList<>());

    public List<ResourceLocation> skills = new ArrayList<>();
    public String stage;

    public SkillTreeSkillStage(String stage, List<ResourceLocation> skills) {
        this.stage = stage;
        this.skills = skills;
    }

    public boolean isEmpty(){
        return this == EMPTY;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        ListTag trees = new ListTag();
        for (ResourceLocation resourceLocation : skills) {
            trees.add(StringTag.valueOf(resourceLocation.toString()));
        }
        nbt.putString("stage", stage);
        nbt.put("skills", trees);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.stage = nbt.getString("stage");
        ListTag trees = (ListTag) nbt.get("skills");
        skills.clear();
        for (Tag tree : trees) {
            skills.add(new ResourceLocation(tree.getAsString()));
        }
    }

    @Override
    public String toString() {
        return "SkillTreeSkillStage{" +
                "skills=" + skills +
                ", stage='" + stage + '\'' +
                '}';
    }
}
