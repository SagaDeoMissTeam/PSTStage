package net.sixik.pststage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.common.util.INBTSerializable;
import net.sixik.pststage.type.SkillTreeSkillStage;
import net.sixik.pststage.type.SkillTreeStage;

import java.util.ArrayList;
import java.util.List;

public class ReloadContainer extends SimplePreparableReloadListener<Void> implements INBTSerializable<CompoundTag> {

    public static final ResourceLocation tree = new ResourceLocation("skilltree:main");

    public static ReloadContainer INSTANCE = new ReloadContainer();

    public SkillTreeStage skillTreeStage = SkillTreeStage.EMPTY;
    public List<SkillTreeSkillStage> skillTasks = new ArrayList<>();

    @Override
    protected Void prepare(ResourceManager p_10796_, ProfilerFiller p_10797_) {
        return null;
    }

    @Override
    protected void apply(Void p_10793_, ResourceManager p_10794_, ProfilerFiller p_10795_) {

    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("SkillTreeStage", skillTreeStage.serializeNBT());
        ListTag sk = new ListTag();

        for (SkillTreeSkillStage skillTask : skillTasks) {
            sk.add(skillTask.serializeNBT());
        }
        tag.put("SkillTasks", sk);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        SkillTreeStage t = new SkillTreeStage("", new ArrayList<>());
        t.deserializeNBT(nbt.getCompound("SkillTreeStage"));
        this.skillTreeStage = t;

        ListTag sk = (ListTag) nbt.get("SkillTasks");
        skillTasks.clear();
        for (Tag tag : sk) {
            SkillTreeSkillStage s = new SkillTreeSkillStage("", new ArrayList<>());
            s.deserializeNBT((CompoundTag) tag);
            skillTasks.add(s);
        }
    }
}
