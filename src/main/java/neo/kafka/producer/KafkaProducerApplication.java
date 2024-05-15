package neo.kafka.producer;

import neo.kafka.producer.model.Student;
import neo.kafka.producer.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KafkaProducerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class);
        KafkaProducerService kafkaProducerService = new KafkaProducerService();
        Student student = new Student();
        student.setName("张三");
        student.setAge(12);
        kafkaProducerService.sendMessage();
        LOGGER.info("Start up kafka producer success.");
    }
}
