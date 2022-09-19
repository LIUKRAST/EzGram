package api.events.joins;

import api.core.TelegramBot;
import api.formatting.IntelligentMessageParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IntelligentTextJoin extends Join {
    private final Path path;
    public IntelligentTextJoin(String id, Path path) {
        super(id);
        this.path = path;
    }

    @Override
    public void call(Update update, TelegramBot loader) {
        SendMessage send = new SendMessage();
        send.setChatId(update.getMessage().getChatId().toString());
        send.setText(code(read(path), update));
        try {
            loader.execute(send);
        } catch (TelegramApiException E) {
            E.printStackTrace();
        }
    }

    private String code(String string, Update update) {
        return IntelligentMessageParser.parseForLogging(string, update);
    }

    private String read(Path path) {
        StringBuilder fullString = new StringBuilder();
        if(Files.exists(path)) {
            File file = new File(path.toString());
            FileReader fr;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader br = new BufferedReader(fr);
            String line;
            while(true) {
                try {
                    line = br.readLine();
                    if(line == null) {
                        break;
                    } else {
                        fullString.append("\n").append(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println(TelegramBot.BOT_ID + "Unable to read file " + path);
        }
        return fullString.toString();
    }
}
