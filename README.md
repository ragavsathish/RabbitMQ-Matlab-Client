# RabbitMQ-Matlab-Client

```
$matlabroot/java/mq-client/matlab-mq-client.jar
$matlabroot/java/mq-client/amqp-client-3.5.4.jar
```

```
evt = mqwrapper.MessagingEvent;
subscriber = mqwrapper.MessageSubscriber(mqwrapper.QueueingConsumerFactory.getConsumer('epoch-echoer', 'localhost'),evt);
java.lang.Thread(subscriber).start
```

```
for i = 1:10
	str = java.lang.String.valueOf(java.lang.System.currentTimeMillis());
	mqwrapper.MessagePublisher.send(java.lang.StringBuilder(str).append(' message ').toString(),'epoch-echoer','localhost');
end
```
