package com.gmail.zendarva.parachronology.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by James on 8/11/2017.
 */
public class PacketHandler {
    private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        // Register messages which are sent from the client to the server here:
        INSTANCE.registerMessage(UseWandPacket.Handler.class, UseWandPacket.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(SendWandBlockPacket.Handler.class,SendWandBlockPacket.class,nextID(), Side.CLIENT);
        INSTANCE.registerMessage(UpdateWandSlotPacket.Handler.class,UpdateWandSlotPacket.class,nextID(),Side.SERVER);
    }
}