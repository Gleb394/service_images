package images.service.api.interview.agileengine;

import org.jboss.resteasy.specimpl.MultivaluedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Component
public class Jwt {

    private static final String URL = "http://interview.agileengine.com/auth";
    private static final String API_KEY_VALUE = "23567b218376f79d9415";

    private final HttpClient client;

    @Autowired
    public Jwt(HttpClient client) {
        this.client = client;
    }

    public String getBearerToken() {
        MultivaluedMap<String, String> body = new MultivaluedTreeMap<>();
        List<String> values = new LinkedList<>();

        values.add(API_KEY_VALUE);
        body.put("apiKey", values);

        Response response = client.getClient().target(URL).request().buildPost(Entity.json(body)).invoke();

        System.out.println(response.readEntity(String.class));

        return response.readEntity(String.class);
    }
}
