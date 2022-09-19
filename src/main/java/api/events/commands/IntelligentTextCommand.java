package api.events.commands;

import api.core.TelegramBot;
import api.formatting.IntelligentMessageParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IntelligentTextCommand extends Command {
    private final Path path;
    public IntelligentTextCommand(String command, Path path) {
        super(command);
        this.path = path;
    }

    @Override
    public void run(Update update, TelegramBot loader) {
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
        if(update.getMessage().getFrom().getUserName() == null) {
            string = string.replace("${USERNAME}", "no_username");
        } else {
            string = string.replace("${USERNAME}", update.getMessage().getFrom().getUserName());
        }
        string = string.replace("${USERID}", update.getMessage().getFrom().getId().toString());
        if(update.getMessage().getFrom().getLastName() == null) {
            string = string.replace("${USER_LASTNAME}", "no_lastname");
        } else {
            string = string.replace("${USER_LASTNAME}", update.getMessage().getFrom().getLastName());
        }
        if(update.getMessage().getFrom().getLanguageCode() == null) {
            string = string.replace("${USER_LANGUAGE}", "no_language");
        } else {
            string = string.replace("${USER_LANGUAGE}", update.getMessage().getFrom().getLanguageCode());
        }
        string = string.replace("${USER_FIRSTNAME}", update.getMessage().getFrom().getFirstName());

        return IntelligentMessageParser.parse(string);
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
