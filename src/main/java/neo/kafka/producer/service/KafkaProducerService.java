package neo.kafka.producer.service;

import com.alibaba.fastjson.JSON;
import neo.kafka.producer.model.Student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerService {
    private static final String NEO_KAFKA_TOPIC = "quickstart-events";
    private KafkaProducer<String, String> producer = null;

    public KafkaProducerService() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.5.128:9092");
        props.put("acks", "0");
        props.put("group.id", "1111");
        props.put("retries", "2");
        //设置key和value序列化方式
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", StringSerializer.class);

        //生产者实例
        producer = new KafkaProducer<String, String>(props);
    }

    public void sendMessage(Student message) {
        ProducerRecord<String, String> record = new ProducerRecord(NEO_KAFKA_TOPIC, JSON.toJSONString(message));
        producer.send(record);
    }
}
