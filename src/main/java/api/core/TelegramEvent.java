package api.core;

import api.core.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class TelegramEvent {
    private String id;
    public TelegramEvent(String id) {
        if(id.isEmpty()) {
            System.out.println(TelegramBot.BOT_ID + "Invalid null id!");
        } else {
            this.id = id;
        }
    }

    public String getId() {return id;}
    public abstract void call(Update update, TelegramBot loader);
}
