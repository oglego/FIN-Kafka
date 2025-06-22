package kd;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class Producer {

    static Random random = new Random();
    static Gson gson = new Gson();

    public static void main(String[] args) throws InterruptedException {
        String topic = "financial-transactions";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            while (true) {
                Transaction txn = generateRandomTransaction();
                String json = gson.toJson(txn);
                String key = txn.accountId;

                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, json);
                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf("Sent: %s%n", json);
                    } else {
                        exception.printStackTrace();
                    }
                });

                Thread.sleep(5000); // Send every 5 seconds
            }
        }
    }

    private static Transaction generateRandomTransaction() {
        String transactionId = UUID.randomUUID().toString();
        String accountId = "acct-" + (1000 + random.nextInt(9000));
        String[] merchants = {"Amazon", "Starbucks", "Uber", "Netflix", "Apple"};
        String merchant = merchants[random.nextInt(merchants.length)];
        double amount = Math.round((10 + random.nextDouble() * 1000) * 100.0) / 100.0;
        String[] currencies = {"USD", "EUR", "GBP"};
        String currency = currencies[random.nextInt(currencies.length)];
        String[] types = {"debit", "credit"};
        String type = types[random.nextInt(types.length)];
        String[] statuses = {"completed", "pending", "failed"};
        String status = statuses[random.nextInt(statuses.length)];
        long timestamp = System.currentTimeMillis();

        return new Transaction(transactionId, accountId, merchant, amount, currency, timestamp, type, status);
    }
}
