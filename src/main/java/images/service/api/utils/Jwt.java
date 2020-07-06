package images.service.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {

    private static final String URL = "http://interview.agileengine.com/auth";
    private static final String API_KEY_VALUE = "23567b218376f79d9415";

    private final HttpClient client;
    private final HttpResponse httpResponse;

    @Autowired
    public Jwt(HttpClient client, HttpResponse httpResponse) {
        this.client = client;
        this.httpResponse = httpResponse;
    }

    public String getBearerToken() throws JsonProcessingException {
        Map<String, String> body = new HashMap<>();
        body.put("apiKey", API_KEY_VALUE);

        httpResponse.parseResponse(client.post(URL, body));

        return httpResponse.getBodyAsMap().get("token");
    }
}
