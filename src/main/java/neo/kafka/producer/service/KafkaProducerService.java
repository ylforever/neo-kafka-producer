package neo.kafka.producer.service;

import com.alibaba.fastjson.JSON;
import com.elon.base.constant.KafkaTopicConst;
import com.elon.base.model.BIReportTask;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


public class KafkaProducerService {
    private KafkaProducer<String, String> producer = null;

    public KafkaProducerService() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.5.128:9092");
        props.put("acks", "0");
        props.put("group.id", "1111");
        props.put("retries", "2");
        props.put("partitioner.class", NeoPartitioner.class);
        //设置key和value序列化方式
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);

        //生产者实例
        producer = new KafkaProducer<>(props);
    }

    /**
     * 外部调用的发消息接口
     */
    public void sendMessage() {
        for (int i = 0; i < 20; ++i) {
            BIReportTask task = new BIReportTask();
            task.setPath("d:/temp");
            task.getParamMap().put("value", String.valueOf(i));
            ProducerRecord<String, String> record = new ProducerRecord(KafkaTopicConst.ELON_TOPIC, JSON.toJSONString(task));
            producer.send(record);
        }
    }
}
