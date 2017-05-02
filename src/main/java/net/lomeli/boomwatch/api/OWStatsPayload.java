package net.lomeli.boomwatch.api;

import net.lomeli.boomwatch.api.stats.RegionStats;

public class OWStatsPayload {
    private RegionStats us, kr, eu;
    private OWHerosPayload heros;

    public OWStatsPayload() {
    }

    public RegionStats getEuStats() {
        return eu;
    }

    public RegionStats getKrStats() {
        return kr;
    }

    public RegionStats getUsStats() {
        return us;
    }

    public RegionStats getFirstValidRegion() {
        return us != null ? us : eu != null ? eu : kr != null ? kr : null;
    }

    public OWHerosPayload getHeros() {
        return heros;
    }

    public void setHeros(OWHerosPayload heros) {
        this.heros = heros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (us != null) sb.append("us=" + us.toString());
        else sb.append("us=null");
        if (eu != null) sb.append(", eu=" + eu.toString());
        else sb.append(", eu=null");
        if (kr != null) sb.append(", kr=" + kr.toString());
        else sb.append(", kr=null");
        if (heros != null) sb.append(", heros=" + heros.toString());
        else sb.append(", heros=null");
        sb.append("}");
        return sb.toString();
    }
}
