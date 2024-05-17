
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Jorge
 */
public class Prueba {

    private static final String EXCHANGE_NAME = "logs";
    private static final String QUEUE_NAME_1 = "queue1";
    private static final String QUEUE_NAME_2 = "queue2";
    private static final String QUEUE_NAME_3 = "queue3";

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public Prueba() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
            channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "");

            channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);
            channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, "");


            System.out.println("Colas RabbitMQ creadas exitosamente.");
        } catch (IOException | TimeoutException e) {
            System.err.println("Error al crear las colas RabbitMQ: " + e.getMessage());
        }
    }

    public void consumirCola(String queueName) throws IOException {
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Mensaje recibido de la cola " + queueName + ": " + message);
            }
        });
    }

    public static void main(String[] args) {
        Prueba consumidor1 = new Prueba();
        Prueba consumidor2 = new Prueba();
        try {
            consumidor1.consumirCola(QUEUE_NAME_1);
            consumidor2.consumirCola(QUEUE_NAME_2);
        } catch (IOException e) {
            System.out.println("Error al consumir la cola: " + e.getMessage());
        }
    }

}
