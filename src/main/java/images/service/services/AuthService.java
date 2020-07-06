package images.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import images.service.api.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class AuthService {

    private final Jwt jwt;

    private String bearerToken;

    @Autowired
    public AuthService(Jwt jwt) {
        this.jwt = jwt;
    }

    @Scheduled(fixedRate = 10)
    public void task() throws JsonProcessingException {
        bearerToken = jwt.getBearerToken();
        System.out.println(bearerToken);
    }
}
