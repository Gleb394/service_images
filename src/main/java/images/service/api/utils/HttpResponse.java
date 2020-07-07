package images.service.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Map;

@Component
public class HttpResponse {

    private final Converter converter;

    private Map<String, Object> bodyAsMap;

    public HttpResponse(Converter converter) {
        this.converter = converter;
    }

    public void parseResponse(Response response) throws JsonProcessingException {
        this.bodyAsMap = converter.converterStringToMap(response.readEntity(String.class));
    }

    public Map<String, Object> getBodyAsMap() {
        return this.bodyAsMap;
    }
}
