package org.incendo.irc.configuration;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * IRC Bot configuration
 */
public interface BotConfiguration {

    /**
     * Get the IRC network the bot is supposed to connect to
     *
     * @return IRC network
     */
    @NonNull String getNetwork();

    /**
     * Get the port the bot is supposed to connect to
     *
     * @return Network port
     */
    int getPort();

    /**
     * Get the channel the bot is supposed to connect to
     *
     * @return Channel name
     */
    @NonNull String getChannel();

    /**
     * Get the bot nickname
     *
     * @return Bot nickname
     */
    @NonNull String getNick();

}
