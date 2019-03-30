package pl.longhorn.simpleChat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import pl.longhorn.simpleChat.service.Broadcaster;
import pl.longhorn.simpleChat.service.MessageService;

@Configuration
public class BeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MessageService messageService(Broadcaster broadcaster) {
        return new MessageService(broadcaster);
    }

    @Bean
    public Broadcaster broadcaster(ObjectMapper objectMapper) {
        return new Broadcaster(objectMapper);
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }
}
