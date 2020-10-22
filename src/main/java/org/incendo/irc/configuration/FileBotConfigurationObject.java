package org.incendo.irc.configuration;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.checkerframework.checker.nullness.qual.NonNull;

@ConfigSerializable
final class FileBotConfigurationObject {

    @Setting(comment = "IRC network")
    private String network = "irc.esper.net";

    @Setting(comment = "IRC port")
    private int port = 6697;

    @Setting("IRC Channel")
    private String channel = "incendo";

    @Setting("Bot nickname")
    private String nickname = "IncendoBot";

    /**
     * Get the IRC network the bot is supposed to connect to
     *
     * @return IRC network
     */
    @NonNull String getNetwork() {
        return this.network;
    }

    /**
     * Get the port the bot is supposed to connect to
     *
     * @return Network port
     */
    int getPort() {
        return this.port;
    }

    /**
     * Get the channel the bot is supposed to connect to
     *
     * @return Channel name
     */
    @NonNull String getChannel() {
        return this.channel;
    }

    /**
     * Get the bot nickname
     *
     * @return Bot nickname
     */
    @NonNull String getNick() {
        return this.nickname;
    }

}
