package net.lomeli.boomwatch.api;

import net.lomeli.boomwatch.api.heros.RegionHeros;

public class OWHerosPayload {
    private RegionHeros us, kr, eu;

    public OWHerosPayload() {
    }

    public RegionHeros getEuStats() {
        return eu;
    }

    public RegionHeros getKrStats() {
        return kr;
    }

    public RegionHeros getUsStats() {
        return us;
    }

    public RegionHeros getFirstValidRegion() {
        return us != null ? us : eu != null ? eu : kr != null ? kr : null;
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
        sb.append("}");
        return sb.toString();
    }
}
