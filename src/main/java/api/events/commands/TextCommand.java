package api.events.commands;

import api.core.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TextCommand extends Command {
    private final String context;
    public TextCommand(String command, String context) {
        super(command);
        this.context = context;
    }

    @Override
    public void run(Update update, TelegramBot loader) {
        SendMessage send = new SendMessage();
        send.setChatId(update.getMessage().getChatId().toString());
        send.setText(context);
        try {
            loader.execute(send);
        } catch (TelegramApiException E) {
            E.printStackTrace();
        }
    }
}
