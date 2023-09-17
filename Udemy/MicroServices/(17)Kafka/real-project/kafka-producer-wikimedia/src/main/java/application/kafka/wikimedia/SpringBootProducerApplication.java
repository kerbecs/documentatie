package application.kafka.wikimedia;


import application.kafka.wikimedia.kafka.WikimediaChangeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class);
    }
    @Autowired
    private WikimediaChangeProducer producer;
    @Bean
    public CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                producer.sendMessage();
            }
        };
    }
}
