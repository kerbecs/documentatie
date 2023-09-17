package application.kafka.wikimedia.kafka;

import application.kafka.wikimedia.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Duration;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class WikimediaChangeProducer {
    @Value("${spring.kafka.topic.name}")
    private String topic1;

    private static final Logger LOGGER = Logger.getLogger(WikimediaChangeProducer.class.getName());

    private final KafkaTemplate<String, String > kafkaTemplate;

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikimediaChangesHandler(topic1,kafkaTemplate);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(url)).build();
        eventSource.start();

    }
}
