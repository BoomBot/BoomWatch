package net.lomeli.boomwatch.api.heros;

import java.util.*;

public class Playtime {
    private Map<String, Float> quickplay, competitive;

    public Playtime() {
    }

    public Map<String, Float> getCompetitivePlaytime() {
        return sortByValue(competitive);
    }

    public Map<String, Float> getQuickplayPlayTime() {
        return sortByValue(quickplay);
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

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
