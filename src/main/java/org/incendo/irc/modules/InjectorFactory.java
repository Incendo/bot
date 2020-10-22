package org.incendo.irc.modules;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Factory that creates {@link Injector} instances
 */
public final class InjectorFactory {

    public @NonNull Injector create() {
        return Guice.createInjector(
                Stage.DEVELOPMENT,
                new ConfigurationModule()
        );
    }

}
