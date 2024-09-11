package net.sixik.pststage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sixik.pststage.network.ModNetwork;
import net.sixik.pststage.network.SendStagesS2C;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PSTStage.MODID)
public class PSTStage {

    public static final String MODID = "pststage";

    @OnlyIn(Dist.CLIENT)
    public static ReloadContainer CLIENT = new ReloadContainer();


    public PSTStage() {
        MinecraftForge.EVENT_BUS.register(this);
        ModNetwork.init();
    }

    @SubscribeEvent
    public void onReload(AddReloadListenerEvent event) {
        event.addListener(ReloadContainer.INSTANCE);
    }

    @SubscribeEvent
    public void onPlayerLoad(PlayerEvent.PlayerLoggedInEvent event){
        if(event.getEntity().getLevel().isClientSide)return;

        new SendStagesS2C(ReloadContainer.INSTANCE.serializeNBT()).sendTo((ServerPlayer) event.getEntity());
    }
}
