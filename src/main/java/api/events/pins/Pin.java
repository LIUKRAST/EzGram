package api.events.pins;

import api.core.TelegramEvent;

public abstract class Pin extends TelegramEvent {
    public Pin(String id) {
        super(id);
    }
}
