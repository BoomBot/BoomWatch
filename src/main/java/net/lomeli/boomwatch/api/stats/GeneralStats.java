package net.lomeli.boomwatch.api.stats;

public class GeneralStats {
    private ModeStats competitive, quickplay;

    public GeneralStats(){}

    public ModeStats getCompetitive() {
        return competitive;
    }

    public ModeStats getQuickplay() {
        return quickplay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (competitive != null) sb.append("competitive=" + competitive.toString());
        else sb.append("competitive=null");
        if (quickplay != null) sb.append(", quickplay=" + quickplay.toString());
        else sb.append(", quickplay=null");
        sb.append("}");
        return sb.toString();
    }
}
