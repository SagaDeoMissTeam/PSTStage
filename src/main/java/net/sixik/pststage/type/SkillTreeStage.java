package net.sixik.pststage.type;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class SkillTreeStage implements INBTSerializable<CompoundTag> {

    public static SkillTreeStage EMPTY = new SkillTreeStage("", new ArrayList<>());

    public List<ResourceLocation> treesID = new ArrayList<>();
    public String stage;

    public SkillTreeStage(String stage, List<ResourceLocation> treesID) {
        this.stage = stage;
        this.treesID = treesID;
    }

    public boolean isEmpty(){
        return this == EMPTY;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        ListTag trees = new ListTag();
        for (ResourceLocation resourceLocation : treesID) {
            trees.add(StringTag.valueOf(resourceLocation.toString()));
        }
        nbt.putString("stage", stage);
        nbt.put("trees", trees);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.stage = nbt.getString("stage");
        ListTag trees = (ListTag) nbt.get("trees");
        treesID.clear();
        for (Tag tree : trees) {
            treesID.add(new ResourceLocation(tree.getAsString()));
        }
    }
}
