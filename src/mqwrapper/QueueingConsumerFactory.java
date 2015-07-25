package mqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class QueueingConsumerFactory {

	private QueueingConsumerFactory() {
	}

	public static QueueingConsumer getConsumer(String exchangeName, String host) {

		QueueingConsumer consumer = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(exchangeName, "fanout");
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, exchangeName, "");

			consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return consumer;

	}
}
