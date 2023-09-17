package com.example.kafka;

import com.example.entity.WikimediaData;
import com.example.repository.WikimediaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class KafkaDataBaseConsumer {
    private final WikimediaDataRepository dataRepository;
    private final Logger logger = Logger.getLogger(KafkaDataBaseConsumer.class.getName());

    @KafkaListener(groupId = "myGroup", topics = "wikimedia_recentchange")
    public void consume(String message){
        logger.info("Message: "+message);

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(message);

        dataRepository.save(wikimediaData);
        logger.info("Object successfully saved.");

    }
}
