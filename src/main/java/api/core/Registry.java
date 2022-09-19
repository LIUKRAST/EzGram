package api.core;

import api.events.commands.Command;
import api.events.joins.Join;
import api.events.leaves.Leave;
import api.events.pins.Pin;

import java.util.ArrayList;
import java.util.Objects;

public class Registry {
    public static final int COMMAND = 0;
    public static final int JOIN_EVENT = 1;
    public static final int LEAVE_EVENT = 2;
    public static final int PIN_EVENT = 3;

    private final String bot_id;

    public ArrayList<Command> COMMANDS = new ArrayList<>();
    public ArrayList<Join> JOIN_EVENTS = new ArrayList<>();
    public ArrayList<Leave> LEAVE_EVENTS = new ArrayList<>();
    public ArrayList<Pin> PIN_EVENTS = new ArrayList<>();

    public Registry(String bot_id) {
        this.bot_id = TelegramBot.BOT_ID + "[" + bot_id + "] ";
    }

    public void register(int registry, TelegramEvent event) {
        if(registry == COMMAND) {
            if(event instanceof Command) {
                if(registerCommand((Command) event)) {
                    System.out.println(bot_id + "Successfully registered command " + ((Command) event).getCommand());
                    COMMANDS.add( (Command)event);
                }
            } else {
                System.out.println(bot_id + "Event not valid for registering COMMAND! " + event);
            }
        } else if(registry == JOIN_EVENT) {
            if(event instanceof Join) {
                if(registerJoin((Join) event)) {
                    System.out.println(bot_id + "Successfully registered join event " + ((Join) event).getId());
                    JOIN_EVENTS.add((Join) event);
                }
            } else {
                System.out.println(bot_id + "Event not valid for registering JOIN_EVENT! " + event);
            }
        } else if(registry == LEAVE_EVENT) {
            if(event instanceof Leave) {
                if(registerLeave((Leave) event)) {
                    System.out.println(bot_id + "Successfully registered leave event " + ((Leave) event).getId());
                    LEAVE_EVENTS.add((Leave) event);
                }
            } else {
                System.out.println(bot_id + "Event not valid for registering LEAVE_EVENT! " + event);
            }
        } else if(registry == PIN_EVENT) {
            if(event instanceof Pin) {
                if(registerPin((Pin) event)) {
                    System.out.println(bot_id + "Successfully registered pin event " + ((Pin) event).getId());
                    PIN_EVENTS.add((Pin) event);
                }
            } else {
                System.out.println(bot_id + "Event not valid for registering PIN_EVENT! " + event);
            }
        } else {
            System.out.println(bot_id + "Theres not event with this ID" + event);
        }

    }

    private boolean registerCommand(Command command) {
        if(COMMANDS.size() != 0) {
            for (Command value : COMMANDS) {
                if (Objects.equals(command.getId(), value.getId())) {
                    System.out.println(bot_id + "Command " + command.getCommand() + "already exists! Please do not register multiple commands");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean registerJoin(Join join) {
        if(JOIN_EVENTS.size() != 0) {
            for (Join join_event : JOIN_EVENTS) {
                if (Objects.equals(join.getId(), join_event.getId())) {
                    System.out.println(bot_id + "Join Event " + join.getId() + " already exists! Please do not register multiple joins with same name");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean registerLeave(Leave leave) {
        if(LEAVE_EVENTS.size() != 0) {
            for (Leave leave_event : LEAVE_EVENTS) {
                if (Objects.equals(leave.getId(), leave_event.getId())) {
                    System.out.println(bot_id + "Leave Event " + leave.getId() + " already exists! Please do not register multiple leaves with same name");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean registerPin(Pin pin) {
        if(PIN_EVENTS.size() != 0) {
            for (Pin pin_event : PIN_EVENTS) {
                if (Objects.equals(pin.getId(), pin_event.getId())) {
                    System.out.println(bot_id + "Pin Event " + pin.getId() + " already exists! Please do not register multiple pins with same name");
                    return false;
                }
            }
        }
        return true;
    }
}
