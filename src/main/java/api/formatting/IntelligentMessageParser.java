package api.formatting;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IntelligentMessageParser {
    public static String parseForLogging(String string, Update update) {
        User inviter = update.getMessage().getFrom();
        StringBuilder usernames = new StringBuilder();
        StringBuilder userids = new StringBuilder();
        StringBuilder user_lastnames = new StringBuilder();
        StringBuilder user_firstnames = new StringBuilder();
        StringBuilder user_languages = new StringBuilder();
        for(User user : update.getMessage().getNewChatMembers()) {
            if(user.getUserName() != null) {
                usernames.append(user.getUserName());
            } else {
                usernames.append(", " + "no_username");
            }
            userids.append(", ").append(user.getId());
            user_firstnames.append(", ").append(user.getFirstName());
            if(user.getLastName() != null) {
                user_lastnames.append(", ").append(user.getLastName());
            } else {
                user_lastnames.append(", " + "no_last_name");
            }
            if(user.getLanguageCode() != null) {
                user_languages.append(", ").append(user.getLanguageCode());
            } else {
                user_languages.append(", " + "no_language");
            }
        }
        string = string.replace("${USERNAMES}", usernames);
        string = string.replace("${USERIDS}", userids);
        string = string.replace("${USER_LASTNAMES}", user_lastnames);
        string = string.replace("${USER_FIRSTNAMES}", user_firstnames);
        string = string.replace("${USER_LANGUAGES}", user_languages);

        if(inviter.getUserName() != null) {
            string = string.replace("${INVITER_USERNAME}", inviter.getUserName());
        } else {
            string = string.replace("${INVITER_USERNAME}", "no_inviter_username");
        }
        string = string.replace("${INVITER_USERID}", inviter.getId().toString());
        if(inviter.getLastName() != null) {
            string = string.replace("${INVITER_LASTNAME}", inviter.getLastName());
        } else {
            string = string.replace("${INVITER_LASTNAME}", "no_inviter_lastname");
        }
        string = string.replace("${INVITER_FIRSTNAME}", inviter.getFirstName());
        if(inviter.getLanguageCode() != null) {
            string = string.replace("${INVITER_LANGUAGE}", inviter.getLanguageCode());
        } else {
            string = string.replace("${INVITER_LANGUAGE}", "no_inviter_language");
        }


        return parse(string);
    }

    public static String parse(String string) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("dd");

        DateTimeFormatter dtf6 = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter dtf7 = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter dtf8 = DateTimeFormatter.ofPattern("ss");
        LocalDateTime now = LocalDateTime.now();

        string = string.replace("${DATE}", dtf.format(now));
        string = string.replace("${TIME}", dtf2.format(now));

        string = string.replace("${YEAR}", dtf3.format(now));
        string = string.replace("${MONTH}", dtf4.format(now));
        string = string.replace("${DAY}", dtf5.format(now));

        string = string.replace("${HOUR}", dtf6.format(now));
        string = string.replace("${MINUTE}", dtf7.format(now));
        string = string.replace("${SECOND}", dtf8.format(now));
        string = string.replace("${RANDOM}", "" + Math.random());
        return string;
    }
}
