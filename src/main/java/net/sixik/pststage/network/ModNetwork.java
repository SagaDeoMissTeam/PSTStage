package net.sixik.pststage.network;

import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;
import net.sixik.pststage.PSTStage;

public interface ModNetwork {
    SimpleNetworkManager NET = SimpleNetworkManager.create(PSTStage.MODID);

    MessageType SEND_STAGES = NET.registerS2C("send_stages", SendStagesS2C::new);

    static void init(){

    }
}
