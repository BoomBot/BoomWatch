package net.lomeli.boomwatch.api.stats;

public class RegionStats {
    private GeneralStats stats;

    public RegionStats() {
    }

    public GeneralStats getStats() {
        return stats;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{stats=");
        if (stats != null) sb.append(stats.toString() + "}");
        else sb.append("null}");
        return sb.toString();
    }
}
