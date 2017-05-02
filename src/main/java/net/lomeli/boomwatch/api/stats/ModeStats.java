package net.lomeli.boomwatch.api.stats;

public class ModeStats {
    private OverallStats overall_stats;
    private boolean competitive;

    public ModeStats() {
    }

    public OverallStats getOverallStats() {
        return overall_stats;
    }

    public boolean isCompetitive() {
        return competitive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (overall_stats != null) sb.append("overall_stats=" + overall_stats.toString());
        else sb.append("overall_stats=null");
        sb.append(", competitive=" + competitive + "}");
        return sb.toString();
    }
}
