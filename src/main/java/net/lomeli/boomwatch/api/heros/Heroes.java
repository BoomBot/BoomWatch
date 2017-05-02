package net.lomeli.boomwatch.api.heros;

public class Heroes {
    private Playtime playtime;

    public Heroes() {
    }

    public Playtime getPlaytime() {
        return playtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{playtime=");
        if (playtime != null) sb.append(playtime.toString() + "}");
        else sb.append("null}");
        return sb.toString();
    }
}
