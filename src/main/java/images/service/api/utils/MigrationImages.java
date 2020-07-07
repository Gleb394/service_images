package images.service.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class MigrationImages {

    private static final String URL_GET_PICTURES = "http://interview.agileengine.com/images";
    private static final String AUTHORIZATION = "Authorization";

    private final HttpClient client;
    private final HttpResponse httpResponse;

    @Autowired
    public MigrationImages(HttpClient client, HttpResponse httpResponse) {
        this.client = client;
        this.httpResponse = httpResponse;
    }

    public List<Map<String, Object>> getAllPicturesId(String token) throws JsonProcessingException {
        List<Map<String, Object>> pictures = new LinkedList<>();
        int page = 0;
        Map<String, Object> requestBody;

        do {
            httpResponse.parseResponse(client.get(URL_GET_PICTURES + "?page=" + page, AUTHORIZATION, token));
            requestBody = httpResponse.getBodyAsMap();

            List<Map<String, Object>> picturesId = (List) requestBody.get("pictures");

            for (Map<String, Object> picture : picturesId) {
                pictures.add(getPicture(token, (String) picture.get("id")));
            }

            page = (int) requestBody.get("page") + 1;

        } while ((boolean) requestBody.get("hasMore"));

        return pictures;
    }

    public Map<String, Object> getPicture(String token, String id) throws JsonProcessingException {
        httpResponse.parseResponse(client.get(URL_GET_PICTURES + "/" + id, AUTHORIZATION, token));

        return httpResponse.getBodyAsMap();
    }
}
