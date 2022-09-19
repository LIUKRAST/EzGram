import api.core.Registry;
import api.core.TelegramBot;
import api.events.commands.IntelligentTextCommand;
import api.events.commands.TextCommand;
import api.events.joins.IntelligentTextJoin;

import java.nio.file.Path;


public class Main {


    public static void main(String[] args) {
        TelegramBot bot1 = new TelegramBot("liuk2_bot", "1878557517:AAEQMxpzAGwd1fx6zxWOKYPKFC1tmn_hNzo", true);

        Path start = Path.of("L:\\PC\\IntelliJProjects\\LiukRastbot\\src\\main\\resources\\start_message.txt");
        Path join = Path.of("L:\\PC\\IntelliJProjects\\LiukRastbot\\src\\main\\resources\\join_message.txt");

        bot1.registry.register(Registry.COMMAND, new IntelligentTextCommand("/start", start));
        bot1.registry.register(Registry.JOIN_EVENT, new IntelligentTextJoin("join", join));
    }
}
