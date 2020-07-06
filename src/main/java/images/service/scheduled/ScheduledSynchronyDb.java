package images.service.scheduled;

import images.service.api.interview.agileengine.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledSynchronyDb {

    private final Jwt jwt;

    private String bearerToken;

    @Autowired
    public ScheduledSynchronyDb(Jwt jwt) {
        this.jwt = jwt;
    }

    @Scheduled(fixedRate = 10)
    public void task() {
        bearerToken = jwt.getBearerToken();
    }
}
