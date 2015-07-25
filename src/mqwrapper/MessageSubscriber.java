package mqwrapper;

import com.rabbitmq.client.QueueingConsumer;

public class MessageSubscriber implements Runnable {

	private boolean processing = false;

	private MessagingEvent event;

	public MessageSubscriber(QueueingConsumer consumer, MessagingEvent event) {
		this.consumer = consumer;
		this.event = event;
	}

	private QueueingConsumer consumer;

	public void run() {

		while (true) {
			try {
				if (!processing) {
					QueueingConsumer.Delivery delivery = consumer.nextDelivery();
					String message = new String(delivery.getBody());
					event.notfifyMatlab(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}

}