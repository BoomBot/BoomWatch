package net.lomeli.boomwatch;

import net.lomeli.boombot.api.Addon;
import net.lomeli.boombot.api.BoomAPI;
import net.lomeli.boombot.api.events.Event;
import net.lomeli.boombot.api.events.bot.PreInitEvent;
import net.lomeli.boombot.api.events.registry.RegisterCommandEvent;
import net.lomeli.boombot.api.util.Logger;

@Addon(addonID = BoomWatch.ID, name = BoomWatch.NAME, version = BoomWatch.VERSION)
public class BoomWatch {
    public static final String ID = "boomwatch";
    public static final String NAME = "BoomWatch";
    public static final String VERSION = "1.0.0";

    @Addon.Instance
    public static BoomWatch INSTANCE;

    public static Logger log;

    @Addon.Event
    public void preInit(PreInitEvent event) {
        log = new Logger(event.getAddon().addonID());
        log.info("Pre-Init");
        BoomAPI.eventRegistry.registerEventHandler(INSTANCE);

    }

    @Event.EventHandler
    public void registerCommand(RegisterCommandEvent event) {
        event.getCommandRegistry().addCommand(INSTANCE, new OWStatsCommand());
    }
}
