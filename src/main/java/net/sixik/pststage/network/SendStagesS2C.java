package net.sixik.pststage.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.sixik.pststage.PSTStage;

public class SendStagesS2C extends BaseS2CMessage {
    public final CompoundTag nbt;

    public SendStagesS2C(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public SendStagesS2C(FriendlyByteBuf buf) {
        this.nbt = buf.readAnySizeNbt();
    }

    @Override
    public MessageType getType() {
        return ModNetwork.SEND_STAGES;
    }

    @Override
    public void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }

    @Override
    public void handle(NetworkManager.PacketContext packetContext) {
        if(packetContext.getEnv().isClient()){
            PSTStage.CLIENT.deserializeNBT(nbt);
        }
    }
}
