package api.events.pins;

import api.core.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TextPin extends Pin {
    private final String text;
    public TextPin(String id, String text) {
        super(id);
        this.text = text;
    }
    @Override
    public void call(Update update, TelegramBot loader) {
        SendMessage send = new SendMessage();
        send.setChatId(update.getMessage().getChatId().toString());
        send.setText(text);
        try {
            loader.execute(send);
        } catch (TelegramApiException E) {
            E.printStackTrace();
        }
    }
}
