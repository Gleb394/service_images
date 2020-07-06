package images.service.api.interview.agileengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {

    private static final String URL = "http://interview.agileengine.com/auth";
    private static final String API_KEY_VALUE = "23567b218376f79d9415";

    private final HttpClient client;
    private final Converter converter;

    @Autowired
    public Jwt(HttpClient client, Converter converter) {
        this.client = client;
        this.converter = converter;
    }

    public Map getBearerToken() throws JsonProcessingException {
        Map<String, String> body = new HashMap<>();
        body.put("apiKey", API_KEY_VALUE);

        String jsonBody = converter.converterToJson(body);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Response response = client.getClient().target(URL).request().buildPost(Entity.json(jsonBody)).invoke();

        return converter.converterStringToMap(response.readEntity(String.class));
    }
}
