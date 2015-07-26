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

1. Define a Matlab call back function 

```Matlab

function myCallbackFcn(hObject, eventData)
  str = java.lang.String.valueOf(java.lang.System.currentTimeMillis());
  obj = java.lang.StringBuilder('Receieved Time [').append(str).append(']').append(eventData.message);
  disp(obj.toString);
end  %myCallbackFcn
```

2. Instantiate MessagingEvent object and assosciate a calback properties for the event.
3. Create a thread to subscribe for message

```Matlab
evt = mqwrapper.MessagingEvent;
h = handle(evt,'CallbackProperties');
set(evt,'ListenCallback',@(h,e)myCallbackFcn(h,e));
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
