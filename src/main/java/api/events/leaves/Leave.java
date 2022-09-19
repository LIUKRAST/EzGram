package api.events.leaves;

import api.core.TelegramEvent;

public abstract class Leave extends TelegramEvent {
    public Leave(String id) {
        super(id);
    }
}
