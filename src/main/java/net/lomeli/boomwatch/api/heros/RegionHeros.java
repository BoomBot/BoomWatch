package net.lomeli.boomwatch.api.heros;

public class RegionHeros {
    private Heroes heroes;

    public RegionHeros() {
    }

    public Heroes getHeros() {
        return heroes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{heros=");
        if (heroes != null) sb.append(heroes.toString() + "}");
        else sb.append("null}");
        return sb.toString();
    }
}
