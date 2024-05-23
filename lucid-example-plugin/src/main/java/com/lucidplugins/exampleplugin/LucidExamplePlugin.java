package com.lucidplugins.exampleplugin;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.movement.pathfinder.Walker;
import net.unethicalite.api.utils.MessageUtils;
import org.pf4j.Extension;
import javax.inject.Inject;
import java.util.logging.Logger;

@Extension
@PluginDescriptor(
        name = "Lucid Example",
        description = "An example plugin that can be copied to use as a plugin skeleton. Does nothing functionally.",
        enabledByDefault = false,
        tags = {"example"}
)
public class LucidExamplePlugin extends Plugin
{

    @Inject
    private Client client;

    @Inject
    private LucidExampleConfig config;

    private Logger log = Logger.getLogger(getName());

    @Provides
    LucidExampleConfig getConfig(final ConfigManager configManager)
    {
        return configManager.getConfig(LucidExampleConfig.class);
    }

    @Override
    protected void startUp()
    {
        log.info(getName() + " Started");

        if (client.getGameState() == GameState.LOGGED_IN)
        {
            MessageUtils.addMessage(getName() + " Started");
        }
    }

    @Override
    protected void shutDown()
    {
        log.info(getName() + " Stopped");
    }

    @Subscribe
    private void onGameTick(final GameTick event)
    {
        WorldPoint worldLocation = Players.getLocal().getWorldLocation();
        worldLocation.dx(1);
        Walker.walkTo(worldLocation);
    }
}