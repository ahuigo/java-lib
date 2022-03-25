package com.projectName;

import java.text.SimpleDateFormat;
import java.util.*;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.*;

public class KafkaConsumer {

    private final ConsumerConnector consumer;
    private static final String TOPIC = "TOPIC_NAME";
    private Jedis redis;
    private static final String REDIS_HOST = "ip";
    private static final int REDIS_PORT = port;
    private static final String REDIS_AUTH = "password";
    private static final String REDIS_QUEUE_NAME = "name";

    private KafkaConsumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", "ip1:port1,ip2:port2/path");

        //group 代表一个消费组
        props.put("group.id", "id");

        //zk连接超时
        props.put("rebalance.backoff.ms", "6000");
        props.put("zookeeper.session.timeout.ms", "6000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "largest");
        props.put("fetch.message.max.bytes", "10000000");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        redis = new Jedis(REDIS_HOST, REDIS_PORT, 3000);
        redis.auth(REDIS_AUTH);
    }

    public void consume() {
        try {
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(TOPIC, new Integer(1));

            StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
            StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

            Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

            KafkaStream<String, String> stream = consumerMap.get(TOPIC).get(0);
            ConsumerIterator<String, String> it = stream.iterator();

            while (it.hasNext()){
                String msg = it.next().message();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now = simpleDateFormat.format(new Date());
                System.out.println(now + " - msg receive size: " + msg.length());
                try{
                    if(redis.llen(REDIS_QUEUE_NAME) < 4000){
                        this.redis.lpush(REDIS_QUEUE_NAME, msg);
                    }
                }catch (JedisConnectionException e) {
                    e.printStackTrace();
                    redis.close();
                    redis = new Jedis(REDIS_HOST, REDIS_PORT, 3000);
                    redis.auth(REDIS_AUTH);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            this.consume();
        }
    }

    public static void main(String[] args) {
        System.out.println("version: 0.0.2");
        new KafkaConsumer().consume();
    }
}
