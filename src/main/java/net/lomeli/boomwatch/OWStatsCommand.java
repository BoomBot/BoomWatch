package net.lomeli.boomwatch;

import com.google.common.base.Strings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.util.Map;
import java.util.Set;

import net.lomeli.boombot.BoomBot;
import net.lomeli.boombot.api.commands.CommandData;
import net.lomeli.boombot.api.commands.CommandResult;
import net.lomeli.boombot.api.commands.ICommand;
import net.lomeli.boombot.api.lib.I18n;
import net.lomeli.boombot.api.util.GuildUtil;
import net.lomeli.boomwatch.api.OWApiUtil;
import net.lomeli.boomwatch.api.OWHerosPayload;
import net.lomeli.boomwatch.api.OWStatsPayload;
import net.lomeli.boomwatch.api.heros.RegionHeros;
import net.lomeli.boomwatch.api.stats.ModeStats;
import net.lomeli.boomwatch.api.stats.OverallStats;

public class OWStatsCommand implements ICommand {
    @Override
    public CommandResult execute(CommandData cmd) {
        if (cmd.getArgs().size() > 1)
            return new CommandResult("boomwatch.command.owstats.error.onearg").setPrivateMessage(true);
        if (cmd.getArgs().size() < 1)
            return new CommandResult("boomwatch.command.owstats.error.specify").setPrivateMessage(true);
        String user = cmd.getArgs().get(0);
        String[] userInfo = user.split("#");
        if (cmd.getArgs().isEmpty() || Strings.isNullOrEmpty(user) || userInfo == null || userInfo.length < 2)
            return new CommandResult("boomwatch.command.owstats.error.specify").setPrivateMessage(true);
        OWStatsPayload payload = OWApiUtil.getPlayerInfo(userInfo[0], userInfo[1]);
        if (payload == null)
            return new CommandResult("boomwatch.command.owstats.error.usernotfound", user).setPrivateMessage(true);
        I18n lang = GuildUtil.getGuildLang(cmd.getGuildData());
        sendFormattedOutput(lang, payload, user, cmd);
        return null;
    }

    private void sendFormattedOutput(I18n lang, OWStatsPayload payload, String user, CommandData data) {
        String avatar = null;
        OverallStats quickplay = payload.getFirstValidRegion().getStats().getQuickplay().getOverallStats();
        OverallStats competitive = null;
        ModeStats comp = payload.getFirstValidRegion().getStats().getCompetitive();
        if (comp != null) competitive = comp.getOverallStats();
        if (quickplay != null) avatar = quickplay.getAvatar();
        else if (competitive != null) avatar = competitive.getAvatar();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(new Color(0xFAA02E));
        builder.setAuthor(user, null, avatar);
        builder.addField(lang.getLocalization("boomwatch.command.owstats.level"), quickplay.getLevel() + "", true);
        builder.addField(lang.getLocalization("boomwatch.command.owstats.quickplay.winrate"), quickplay.getWinRate() + "%", true);
        if (competitive != null) {
            builder.addField(lang.getLocalization("boomwatch.command.owstats.competitive.rank"), "" + competitive.getCompetitiveRank(), true);
            builder.addField(lang.getLocalization("boomwatch.command.owstats.competitive.winrate"), competitive.getWinRate() + "%", true);
        }
        if (payload.getHeros() != null) addHeroProtraits(payload.getHeros(), builder);

        MessageEmbed msg = builder.build();
        Guild guild = BoomBot.jda.getGuildById(data.getGuildID());
        TextChannel channel = guild.getTextChannelById(data.getChannelID());
        channel.sendMessage(msg).submit();
    }

    private void addHeroProtraits(OWHerosPayload payload, EmbedBuilder builder) {
        RegionHeros region = payload.getFirstValidRegion();
        if (region != null && region.getHeros() != null && region.getHeros().getPlaytime() != null) {
            Map<String, Float> heroMap = region.getHeros().getPlaytime().getFirstUsableMap();
            if (!heroMap.isEmpty()) {
                String hero = getTopHeroFromMap(heroMap.entrySet());
                builder.setThumbnail(getHeroPortait(hero));
            }
        }
    }

    private String getTopHeroFromMap(Set<Map.Entry<String, Float>> heroSet) {
        String hero = null;
        float time = 0;
        for (Map.Entry<String, Float> playtime : heroSet) {
            if (playtime.getValue() >= time) {
                hero = playtime.getKey();
                time = playtime.getValue();
            }
        }
        return hero;
    }

    private String getHeroPortait(String heroname) {
        if (heroname.equalsIgnoreCase("soldier76")) heroname = "soldier-76";
        return String.format("https://blzgdapipro-a.akamaihd.net/hero/%s/hero-select-portrait.png", heroname);
    }

    @Override
    public String getName() {
        return "owstats";
    }

    @Override
    public boolean canUserExecute(CommandData cmd) {
        return true;
    }

    @Override
    public boolean canBotExecute(CommandData cmd) {
        return true;
    }

    @Override
    public CommandResult failedToExecuteMessage(CommandData cmd) {
        return null;
    }
}
