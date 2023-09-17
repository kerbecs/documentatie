package application.kafka.wikimedia;


import application.kafka.wikimedia.kafka.WikimediaChangeProducer;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class WikimediaChangesHandler implements EventHandler {
    private static final Logger LOGGER = Logger.getLogger(WikimediaChangesHandler.class.getName());

    private final String topic1;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event data -> $s",messageEvent.getData()));

        kafkaTemplate.send(topic1,messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
