package net.lomeli.boomwatch.api.stats;

import com.google.common.base.Strings;

public class OverallStats {
    private float win_rate;
    private int level, prestige, comprank, losses;
    private String avatar;

    public OverallStats() {
    }

    public float getWinRate() {
        return win_rate;
    }

    public int getLevel() {
        return (prestige * 100) + level;
    }

    public int getCompetitiveRank() {
        return comprank;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return String.format("{win_rate=%s, level=%s, prestige=%s, comprank=%s, losses=%s, avatar=%s}",
                win_rate, level, prestige, comprank, losses,
                (Strings.isNullOrEmpty(avatar) ? "null" : avatar));
    }
}
