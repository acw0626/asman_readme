package taxiguider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import taxiguider.수리기사호출RepositoryListener;

@Configuration
@EnableMongoAuditing
public class TaxicallAppConfig {
	@Bean
    public 수리기사호출RepositoryListener 수리기사호출RepositoryListener() {
        return new 수리기사호출RepositoryListener();
    }

}
