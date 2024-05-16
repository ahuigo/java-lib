package jproj;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.ByteSequence;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.Watch.Watcher;
import io.etcd.jetcd.options.WatchOption;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;

public class AppTest {

    @Test
    public void testEtcdCrud() {
        Client client = Client.builder().endpoints("http://etcdlocal:2379")
                .build();
        KV kvClient = client.getKVClient();
        ByteSequence key = ByteSequence.from("/conf2/test_proj/dev/test.custom".getBytes());
        ByteSequence value = ByteSequence.from("test_value".getBytes());

        try {
            // put the key-value
            kvClient.put(key, value).get();

            // get the CompletableFuture
            CompletableFuture<GetResponse> getFuture = kvClient.get(key);

            // get the value from CompletableFuture
            GetResponse response = getFuture.get();
            response.getKvs().forEach(kv -> {
                ByteSequence k = kv.getKey();
                ByteSequence v = kv.getValue();
                System.out.printf("\nlatest config:\nkey=%s, value=%s\n", k.toString(), v.toString());
            });

            // delete the key
            kvClient.delete(key).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testEtcdPoll() {
        // 轮询方式
        Client client = Client.builder().endpoints("http://etcdlocal:2379")
                .build();
        KV kvClient = client.getKVClient();

        try {
            // get the CompletableFuture
            ByteSequence key = ByteSequence.from("/a/b.custom".getBytes());

            // get the value from CompletableFuture
            for (int i = 0; i < 10; i++) {
                CompletableFuture<GetResponse> getFuture = kvClient.get(key);
                GetResponse response = getFuture.get();
                response.getKvs().forEach(kv -> {
                    ByteSequence k = kv.getKey();
                    ByteSequence v = kv.getValue();
                    System.out.printf("\nlatest config:\nkey=%s, value=%s\n", k.toString(), v.toString());
                });

                Thread.sleep(2000); // Sleep for one second
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    // example:
    // https://github.com/etcd-io/jetcd/blob/main/jetcd-ctl/src/main/java/io/etcd/jetcd/examples/ctl/CommandWatch.java
    public void testEtcdWatch() {
        Integer maxEvents = 100;
        CountDownLatch latch = new CountDownLatch(maxEvents != null ? maxEvents : Integer.MAX_VALUE);
        Logger LOGGER = LoggerFactory.getLogger("test watcher");

        try (Client client = Client.builder().endpoints("http://etcdlocal:2379").build()) {
            String key = "/c";
            ByteSequence keyBs = ByteSequence.from(key.getBytes());
            // WatchOption watchOpts = WatchOption.builder().withRevision(10).build();
            Watcher watcher2 = client.getWatchClient().watch(keyBs, response -> {
                for (WatchEvent event : response.getEvents()) {
                    System.out.printf("type: %s,key:%s, value:\n%s\n\n",
                            event.getEventType(),
                            event.getKeyValue().getKey(),
                            event.getKeyValue().getValue());
                }
            });

            latch.await();

            LOGGER.info("done");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

}