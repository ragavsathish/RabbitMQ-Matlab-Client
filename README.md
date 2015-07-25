# RabbitMQ-Matlab-Client

This is an example implementation of [java to matlab callback ](http://undocumentedmatlab.com/blog/matlab-callbacks-for-java-events "Title") which can be used to subscribe message from RabbitMQ and trigger the matlab call back function.


#### Setup

1. Download the binary rabbit-mq-matlab-wrapper.jar from release. 
2. Set the matlab static classpath with below entries
..
```
<location of jar>/rabbit-mq-matlab-wrapper
<location of jar>/amqp-client-3.5.4.jar
```

#### Usage

Subscribe message 

```Matlab
evt = mqwrapper.MessagingEvent;
subscriber = mqwrapper.MessageSubscriber(mqwrapper.QueueingConsumerFactory.getConsumer('epoch-echoer', 'localhost'),evt);
java.lang.Thread(subscriber).start
```

Publish messages to queue

```Matlab
for i = 1:10
	str = java.lang.String.valueOf(java.lang.System.currentTimeMillis());
	mqwrapper.MessagePublisher.send(java.lang.StringBuilder(str).append(' message ').toString(),'epoch-echoer','localhost');
end
```
