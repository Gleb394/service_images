package images.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import images.service.api.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenHelper jwt;

    @Autowired
    public AuthService(TokenHelper jwt) {
        this.jwt = jwt;
    }

    public String refreshToken() throws JsonProcessingException {
        return jwt.getBearerToken();
    }
}
