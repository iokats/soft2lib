/*******************************************************************************
 * Copyright 2018 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.zarkary.designpatterns.eventaggregator.example.items;

import com.zarkary.designpatterns.eventaggregator.api.*;

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
