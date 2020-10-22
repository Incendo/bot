package org.incendo.irc.commands;

import cloud.commandframework.arguments.CommandArgument;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.execution.CommandExecutionHandler;
import cloud.commandframework.pircbotx.PircBotXCommandManager;
import cloud.commandframework.pircbotx.arguments.UserArgument;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.irc.configuration.BotConfiguration;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

public final class UwuCommand implements CommandExecutionHandler<User> {

    private final CommandArgument<User, User> userArgument;

    private BotConfiguration botConfiguration;
    private PircBotX bot;

    public UwuCommand(
            final @NonNull BotConfiguration configuration,
            final @NonNull PircBotX pircBotX,
            final @NonNull PircBotXCommandManager<User> commandManager
    ) {
        this.userArgument = UserArgument.of("user");
        commandManager.command(
                commandManager.commandBuilder("uwu")
                              .argument(this.userArgument)
                              .handler(this)
        );
        this.botConfiguration = configuration;
        this.bot = pircBotX;
    }

    @Override
    public void execute(
            @NonNull final CommandContext<User> commandContext
    ) {
        bot.send().message(
                "#" + this.botConfiguration.getChannel(),
                Colors.RED + "o((*^▽^*))o ~uwu~ " + Colors.PURPLE + commandContext.get(this.userArgument).getNick()
        );
    }

}
