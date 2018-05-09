package com.zarkary.designpatterns.eventaggregator.example.items;

import com.zarkary.designpatterns.eventaggregator.api.IEventType;

public enum Event implements IEventType {
    ITEM_CREATED, ITEM_SAVED, ITEM_SELECTED
}
