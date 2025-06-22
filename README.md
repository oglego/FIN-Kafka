# Kafka Financial Data Simulator (Java, Kafka, and Docker)

To learn more about Java, Kafka, and Docker we implement this project to simulate random financial transactions and stream them to an Apache Kafka topic using a simple Java producer. The project includes a producer that emits randomized transaction data as JSON and a consumer that reads and prints them.

---

## Features

* Java Kafka producer that sends realistic financial transactions
* JSON message structure (includes account, amount, merchant, status, etc.)
* Kafka consumer that reads from the topic
* Docker Compose setup for Kafka and Zookeeper
* Easy to run using Maven

---

## Project Structure

```
.
├── docker-compose.yml
├── pom.xml
├── README.md
└── src
    └── main
        └── java
            └── kd
                ├── Transaction.java
                ├── Producer.java
                └── Consumer.java
```

---

## Prerequisites

* Docker & Docker Compose
* Java 17+
* Maven 3+

---

## Setup

### 1. Start Kafka and Zookeeper

```bash
docker-compose up -d
```

This launches Kafka on `localhost:9092`.

### 2. Compile the Java project

```bash
mvn compile
```

---

## Run the Producer

To run the financial transaction simulator:

```bash
mvn compile exec:java -Dexec.mainClass="kd.Producer"
```

It will send a JSON transaction to Kafka every 5 seconds.

---

## Run the Consumer

To consume messages from the `financial-transactions` topic:

```bash
mvn compile exec:java -Dexec.mainClass="kd.Consumer"
```

You’ll see each incoming JSON transaction printed to the terminal.

---

## Example Message

```json
{
  "transactionId": "c233f0d3-99f1-4e3d-a598-daa6e6e47c23",
  "accountId": "acct-4956",
  "merchant": "Amazon",
  "amount": 284.12,
  "currency": "USD",
  "timestamp": 1719005594123,
  "type": "debit",
  "status": "completed"
}
```

---

## Kafka Topic Used

* **Topic Name:** `financial-transactions`

---

## Inspiration

This is a great foundation for:

* Real-time fraud detection
* Stream processing with Kafka Streams or Flink
* Financial dashboards or anomaly detection demos
