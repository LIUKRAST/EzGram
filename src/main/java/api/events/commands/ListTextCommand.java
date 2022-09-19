package api.events.commands;

import api.core.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class ListTextCommand extends Command {
    private final ArrayList<String> strings;
    public ListTextCommand(String command, ArrayList<String> strings) {
        super(command);
        this.strings = strings;
    }

    @Override
    public void run(Update update, TelegramBot loader) {
        String context = strings.get( (int)(Math.random() * strings.size()) );
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
