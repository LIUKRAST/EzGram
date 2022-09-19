package api.core;

import api.events.commands.Command;
import api.events.joins.Join;
import api.events.leaves.Leave;
import api.events.pins.Pin;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot extends TelegramLongPollingBot {

    public static final String BOT_ID = "[EZGRAM] ";
    private final String username;
    private final String token;
    private boolean log;
    public Registry registry;


    public TelegramBot(String username, String token, boolean log) {
        this.username = username;
        this.token = token;
        this.log = log;
        this.registry = new Registry(username);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            this.setLog(true);
        }
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String log = BOT_ID + "Update performed. Reason: ";
        if(update.getMessage().getNewChatMembers().size() > 0) {
            // CASE JOIN EVENT
            if(registry.JOIN_EVENTS.size() != 0) {
                for (int i = 0; i <= registry.JOIN_EVENTS.size(); i++) {
                    Join join = registry.JOIN_EVENTS.get(i);
                    join.call(update, this);
                }
            }
            log = log + "JOIN_EVENT";
        } else if(update.getMessage().getLeftChatMember() != null) {
            // CASE LEAVE EVENT
            if(registry.LEAVE_EVENTS.size() != 0) {
                for (int i = 0; i <= registry.LEAVE_EVENTS.size(); i++) {
                    Leave leave = registry.LEAVE_EVENTS.get(i);
                    leave.call(update, this);
                }
            }
            log = log + "LEAVE_EVENT";
        } else if(update.getMessage().getPinnedMessage() != null) {
            // CASE PIN EVENT
            if(registry.PIN_EVENTS.size() != 0) {
                for (int i = 0; i <= registry.PIN_EVENTS.size(); i++) {
                    Pin pin = registry.PIN_EVENTS.get(i);
                    pin.call(update, this);
                }
            }
            log = log + "PIN_EVENT";
        } else {
            if(registry.COMMANDS.size() != 0) {
                for (int i = 0; i <= registry.COMMANDS.size(); i++) {
                    Command command = registry.COMMANDS.get(i);
                    if (
                            update.getMessage().getText().startsWith(command.getCommand() + " ") || update.getMessage().getText().startsWith(command.getCommand() + "@" + this.username + " ")
                                    || update.getMessage().getText().equals(command.getCommand()) || update.getMessage().getText().equals(command.getCommand() + "@" + this.username)
                    ) {
                        command.run(update, this);
                        log = log + "COMMAND_EVENT, Sender: " + update.getMessage().getFrom().getUserName();
                        break;
                    }
                }
            }
        }
        if(this.log) {
            System.out.println(log);
        }
    }
}
