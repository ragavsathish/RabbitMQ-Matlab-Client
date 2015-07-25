package mqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessagePublisher {

	public static void send(String message, String exchangeName, String host) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(exchangeName, "fanout");
		channel.basicPublish(exchangeName, "", null, message.getBytes());
		channel.close();
		connection.close();
	}
}
