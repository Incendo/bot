package org.incendo.irc.configuration;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.typesafe.config.ConfigRenderOptions;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.AbstractConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.irc.annotations.BotDirectory;

import java.io.File;
import java.io.IOException;

/**
 * File based implementation of {@link BotConfiguration}
 */
public class FileBotConfiguration implements BotConfiguration {

    private final File directory;
    private FileBotConfigurationObject fileBotConfigurationObject;

    @Inject
    public FileBotConfiguration(final @NonNull @BotDirectory File directory) {
        this.directory = directory;
        this.loadConfiguration();
    }

    public void loadConfiguration() {
        final File configFile = new File(this.directory, "bot.conf");
        final AbstractConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader
                .builder()
                .setRenderOptions(ConfigRenderOptions
                        .defaults()
                        .setComments(true)
                        .setFormatted(true)
                        .setOriginComments(false)
                        .setJson(false))
                .setDefaultOptions(ConfigurationOptions.defaults())
                .setFile(configFile)
                .build();

        FileBotConfigurationObject configurationObject = null;
        ConfigurationNode configurationNode;

        try {
            configurationNode = loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
            configurationNode = loader.createEmptyNode();
        }

        if (!configFile.exists()) {
            configurationObject = new FileBotConfigurationObject();
            try {
                final CommentedConfigurationNode commentedConfigurationNode = loader.createEmptyNode();
                commentedConfigurationNode.setComment("");
                loader.save(commentedConfigurationNode.setValue(
                        configurationObject
                ));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                configurationObject = configurationNode.getValue(
                        TypeToken.of(FileBotConfigurationObject.class),
                        new FileBotConfigurationObject()
                );
            } catch (final ObjectMappingException e) {
                e.printStackTrace();
            }
        }

        this.fileBotConfigurationObject = configurationObject;
    }

    @Override
    public @NonNull String getNetwork() {
        return this.fileBotConfigurationObject.getNetwork();
    }

    @Override
    public int getPort() {
        return this.fileBotConfigurationObject.getPort();
    }

    @Override
    public @NonNull String getChannel() {
       return this.fileBotConfigurationObject.getChannel();
    }

    @Override
    public @NonNull String getNick() {
        return this.fileBotConfigurationObject.getNick();
    }

}
