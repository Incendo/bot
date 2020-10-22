package org.incendo.irc;

import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.internal.CommandRegistrationHandler;
import cloud.commandframework.pircbotx.PircBotXCommandManager;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.irc.commands.UwuCommand;
import org.incendo.irc.configuration.BotConfiguration;
import org.incendo.irc.modules.InjectorFactory;
import org.incendo.irc.modules.PircBotXFactory;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.exception.IrcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Function;

@Singleton
public class IrcBot {

    public static final Logger LOGGER = LoggerFactory.getLogger(IrcBot.class);

    @Inject
    public IrcBot(
            final @NonNull BotConfiguration configuration,
            final @NonNull PircBotXFactory pircBotXFactory
    ) {
        LOGGER.info("Setting up the bot...");
        final PircBotX bot = pircBotXFactory.create();
        final PircBotXCommandManager<User> commandManager = new PircBotXCommandManager<>(
                bot,
                AsynchronousCommandExecutionCoordinator.<User>newBuilder().build(),
                CommandRegistrationHandler.nullCommandRegistrationHandler(),
                (user, perm) -> user.isIrcop(),
                Function.identity(),
                "~"
        );
        new UwuCommand(
                configuration,
                bot,
                commandManager
        );
        try {
            LOGGER.info("Starting the bot...");
            bot.startBot();
        } catch (IOException | IrcException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public static void main(final String[] args) {
        final Injector injector = new InjectorFactory().create();
        injector.getInstance(IrcBot.class);
    }

}
