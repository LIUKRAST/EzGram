package api.events.commands;

import api.core.TelegramBot;
import api.core.TelegramEvent;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command extends TelegramEvent {
    private final String command;

    public Command(String command) {
        super(command);
        this.command = command;
    }

    public abstract void run(Update update, TelegramBot loader);

    public void call(Update update, TelegramBot loader) {
        this.run(update, loader);
    };

    public String getCommand() {
        return command;
    }
}
