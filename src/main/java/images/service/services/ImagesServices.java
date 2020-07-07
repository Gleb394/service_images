package images.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import images.service.api.utils.MigrationImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class ImagesServices {

    private final MigrationImages migration;
    private final AuthService authService;

    @Autowired
    public ImagesServices(MigrationImages migration, AuthService authService) {
        this.migration = migration;
        this.authService = authService;
    }

    @Scheduled(cron = "0 30 9 ? * MON-FRI", zone = "Europe/Kiev")
    public void save() throws JsonProcessingException {
        List<Map<String, Object>> pictures;

        String token = authService.refreshToken();

        pictures = migration.getAllPicturesId(token);

    }
}
