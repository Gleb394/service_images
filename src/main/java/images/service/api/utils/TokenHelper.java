package images.service.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class TokenHelper {

    private static final String URL = "http://interview.agileengine.com/auth";
    private static final String API_KEY_VALUE = "23567b218376f79d9415";

    private final HttpClient client;
    private final HttpResponse httpResponse;

    private String token;

    @Autowired
    public TokenHelper(HttpClient client, HttpResponse httpResponse) {
        this.client = client;
        this.httpResponse = httpResponse;
    }

    public String getBearerToken() throws JsonProcessingException {
        if (isNull(token)) {
            Map<String, String> body = new HashMap<>();
            body.put("apiKey", API_KEY_VALUE);

            httpResponse.parseResponse(client.post(URL, body));

            token = (String) httpResponse.getBodyAsMap().get("token");
        }

        return token;
    }
}
