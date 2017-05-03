package net.lomeli.boomwatch.api.heros;

import java.util.*;

public class Playtime {
    private Map<String, Float> quickplay, competitive;

    public Playtime() {
    }

    public Map<String, Float> getCompetitivePlaytime() {
        return Collections.unmodifiableMap(competitive);
    }

    public Map<String, Float> getQuickplayPlayTime() {
        return Collections.unmodifiableMap(quickplay);
    }

    public Map<String, Float> getFirstUsableMap() {
        return quickplay != null ? getQuickplayPlayTime() : competitive != null ? getCompetitivePlaytime() :
                Collections.EMPTY_MAP;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (quickplay != null && !quickplay.isEmpty()) sb.append("quickplay=" + quickplay.toString() + ", ");
        else sb.append("quickplay=null, ");
        if (competitive != null && !competitive.isEmpty()) sb.append("competitive=" + quickplay.toString() + "}");
        else sb.append("competitive=null}");
        return sb.toString();
    }
}
