package images.service.api.interview.agileengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static java.util.Objects.isNull;

@Component
public class HttpClient {

    private Client client;

    public Client getClient() {
        if (isNull(client)) {
            client = ClientBuilder.newClient();
        }

        return client;
    }
}
