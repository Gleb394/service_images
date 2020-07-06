package images.service.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class HttpClient {

    private final Converter converter;

    private Client client;

    @Autowired
    public HttpClient(Converter converter) {
        this.converter = converter;
    }

    private Client getClient() {
        if (isNull(client)) {
            client = ClientBuilder.newClient();
        }

        return client;
    }

    public Response post(String url, Map<String, String> body) throws JsonProcessingException {
        String jsonBody = converter.converterToJson(body);

        return getClient().target(url).request().buildPost(Entity.json(jsonBody)).invoke();
    }

    public Response get(String url, String headerKey, String headerValue) {
        return getClient().target(url).request().header(headerKey, headerValue).get();
    }
}
