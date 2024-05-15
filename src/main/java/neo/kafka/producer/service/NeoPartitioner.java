package neo.kafka.producer.service;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区算法
 *
 * @author neo
 * @since 2024-05-10
 */
public class NeoPartitioner implements Partitioner {
    // 分区数量
    private static final int PARTITION_NUM = 5;

    private RoundRobinPartitioner roundRobinPartitioner = new RoundRobinPartitioner();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (key == null || "".equals(key)) {
            // 未指定分区键，使用轮循分区（指定了key的消息不参与）
            return roundRobinPartitioner.partition(topic, key, keyBytes, value, valueBytes, cluster);
        }

        // 基于业务的约定. 某些消息放到固定的分区
        if ("Tom".equals(key)) {
            return 1;
        }

        if ("Alice".equals(key)) {
            return 2;
        }

        // 其它情况按key的哈希值分区
        int keyCode = key.hashCode();
        return keyCode % PARTITION_NUM;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
