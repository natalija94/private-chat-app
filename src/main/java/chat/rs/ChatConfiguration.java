package chat.rs;

import chat.rs.util.RestConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author natalija
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
@EnableAsync
@ComponentScan("chat.rs")
public class ChatConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Configuration for message broker.
     * Client can expect discussion results via web socket if it is subscribed to bellow configured destinations.
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(RestConstants.PART_OF_DISCUSSION_PATH, RestConstants.FULL_DISCUSSION_PATH);
        config.setApplicationDestinationPrefixes(RestConstants.DISCUSSION_GLOBAL_PATH + RestConstants.SYNCHRONIZE);
    }

    /**
     * Register STOMP as endpoint(s) given below from defined origins.
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint(RestConstants.DISCUSSION_GLOBAL_PATH + RestConstants.SYNCHRONIZE)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
