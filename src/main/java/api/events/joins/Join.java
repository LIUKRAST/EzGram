package api.events.joins;

import api.core.TelegramEvent;

public abstract class Join extends TelegramEvent {
    public Join(String id) {
        super(id);
    }
}
