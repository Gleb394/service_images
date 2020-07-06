package images.service.scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import images.service.api.interview.agileengine.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableScheduling
public class ScheduledSynchronyDb {

    private final Jwt jwt;

    private Map bearerToken;

    @Autowired
    public ScheduledSynchronyDb(Jwt jwt) {
        this.jwt = jwt;
    }

    @Scheduled(fixedRate = 10)
    public void task() throws JsonProcessingException {
        bearerToken = jwt.getBearerToken();
        System.out.println(bearerToken);
    }
}
