package net.lomeli.boomwatch.api;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;

import net.lomeli.boomwatch.BoomWatch;

public class OWApiUtil {
    public static final Gson GSON = new Gson();
    public static final HttpClient client = HttpClients.createMinimal();

    public static OWStatsPayload getPlayerInfo(String playerName, String playerID) {
        if (Strings.isNullOrEmpty(playerName) || Strings.isNullOrEmpty(playerID)) return null;
        OWStatsPayload payload = null;
        try {
            HttpResponse response = GET(String.format("https://owapi.net/api/v3/u/%s-%s/stats", playerName, playerID));
            HttpEntity entity = response.getEntity();
            if (entity != null && entity.getContentLength() > 0) {
                InputStreamReader reader = new InputStreamReader(entity.getContent());
                payload = GSON.fromJson(reader, OWStatsPayload.class);
            }
        } catch (IOException ex) {
            BoomWatch.log.error("Failed to get response from OWApi!", ex);
        }
        if (payload != null) {
            try {
                HttpResponse response = GET(String.format("https://owapi.net/api/v3/u/%s-%s/heroes", playerName, playerID));
                HttpEntity entity = response.getEntity();
                if (entity != null && entity.getContentLength() > 0) {
                    InputStreamReader reader = new InputStreamReader(entity.getContent());
                    payload.setHeros(GSON.fromJson(reader, OWHerosPayload.class));
                }
            } catch (IOException ex) {
                BoomWatch.log.error("Failed to get response from OWApi!", ex);
            }
        }
        return payload;
    }

    public static HttpResponse GET(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        return client.execute(get);
    }
}
