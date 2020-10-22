package org.incendo.irc.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.incendo.irc.annotations.BotDirectory;
import org.incendo.irc.configuration.BotConfiguration;
import org.incendo.irc.configuration.FileBotConfiguration;

import java.io.File;

class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        final File file = new File("./ircbot");
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new IllegalStateException("Could not create bot directory");
            }
        }
        this.bind(File.class).annotatedWith(BotDirectory.class).toInstance(file);
        this.bind(BotConfiguration.class).to(FileBotConfiguration.class).in(Singleton.class);
    }

}
