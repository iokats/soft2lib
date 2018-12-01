**Event Aggregator a Java Implementation**
------------------------------------------


The Event Aggregator consist of three distinct parts:

* The **publishers**, which publish different kind of **Messages**.
* The **Subscribers**, which are interested for different kind of **Messages**.
* And the **Event Aggregator**, which aggregates all the **Messages** that are emitted by the Publishers and 
  transmit them in every interested Subscriber. 


**How It Works**
----------------

The Publishers publish different type of Messages via the Event Aggregator. The Event Aggregator from its side
propagate these Messages to all interested Subscribers.

Each published **Message** has a distinct **IEventType** and a specific **Content**.

Every Subscriber is registered to the Event Aggregator in order to get notifications for specific IEventType
of Messages. 

Also, every subscriber assign, for every IEvenType which is interested, a specific Action. 
So, when it gets a notification relevant for a specific IEventType, then the corresponding Action is executed.  

Every Subscriber can watch more than one IEventType of Messages and also every Publisher can publish more than one
different IEventType of Messages.


**Event Aggregator UML Diagram**
--------------------------------

![UML Diagram](EventAggregator.png "Event Aggregator UML Diagram")


**Code Example**
----------------

```java
public enum Event implements IEventType {
    ITEM_CREATED, ITEM_SAVED, ITEM_SELECTED,
}
```

```java
public class Item {

    private final int itemNumber;

    public Item(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }
}
```

```java
public class App {

    public static void main(String[] args) {

        final EventAggregator eventAggregator = new EventAggregator();

        final ISubscriber itemView = new Subscriber(eventAggregator);
        itemView.subscribe(Event.ITEM_CREATED, message ->
                System.out.println("Item created " + ((Item)message.getContent()).getItemNumber())
        );

        itemView.subscribe(Event.ITEM_SAVED, message ->
                System.out.println("Item saved " + ((Item)message.getContent()).getItemNumber())
        );

        itemView.subscribe(Event.ITEM_SELECTED, message ->
                System.out.println("Item selected " + ((Item)message.getContent()).getItemNumber())
        );

        final IPublisher publisher = new Publisher(eventAggregator);

        final Item item1 = new Item(1);
        final Item item2 = new Item(2);
        final Item item3 = new Item(3);

        System.out.println("\n******************** ITEM_CREATED ********************");
        publisher.publish(Event.ITEM_CREATED, item1);
        publisher.publish(Event.ITEM_CREATED, item2);
        publisher.publish(Event.ITEM_CREATED, item3);
        System.out.println("******************************************************");

        System.out.println("\n********************* ITEM_SAVED *********************");
        publisher.publish(Event.ITEM_SAVED, item1);
        publisher.publish(Event.ITEM_SAVED, item3);
        System.out.println("******************************************************");

        System.out.println("\n******************* ITEM_SELECTED ********************");
        publisher.publish(Event.ITEM_SELECTED, item2);
        publisher.publish(Event.ITEM_SELECTED, item3);
        System.out.println("******************************************************");
    }
}
```
