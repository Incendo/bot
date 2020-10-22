package org.incendo.irc.modules;

import com.google.inject.Inject;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.irc.configuration.BotConfiguration;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Factory that produces {@link org.pircbotx.PircBotX} instances
 */
public final class PircBotXFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PircBotXFactory.class);

    private final BotConfiguration configuration;

    @Inject
    public PircBotXFactory(final @NonNull BotConfiguration configuration) {
        this.configuration = configuration;
    }

    public @NonNull PircBotX create() {
        LOGGER.info(
                "Creating bot that will connect to #{} @ {}/{}",
                this.configuration.getChannel(),
                this.configuration.getNetwork(),
                this.configuration.getPort()
        );
        final Configuration configuration = new Configuration.Builder()
                .setName(this.configuration.getNick())
                .setLogin(this.configuration.getNick())
                .setAutoNickChange(true)
                .setAutoReconnect(true)
                .addServer(this.configuration.getNetwork(), this.configuration.getPort())
                .addAutoJoinChannels(Collections.singletonList("#" + this.configuration.getChannel()))
                .setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates().disableDiffieHellman())
                .buildConfiguration();
        return new PircBotX(configuration);
    }

}
