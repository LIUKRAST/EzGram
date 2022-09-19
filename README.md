# Welcome to EZGRAM

-------------

Trying to simplify telegram bot creation using a simple API.

Welcome to ezgram. Im still working on a wiki, so here is how you create a new bot.

* Step 1, registering a new bot
```
public void main(String[] args) {
    TelegramBot MY_NEW_BOT = new TelegramBot("BOT_USERNAME", "BOT_TOKEN", false)
}
```
* Step 2, adding your events

```
//add a new command
MY_NEW_BOT.registry.register(Registry.COMMAND, new COMMAND_TYPE);
//add a new join event
MY_NEW_BOT.registry.register(Registry.JOIN_EVENT, new JOIN_EVENT_TYPE);
//add a new leave event
MY_NEW_BOT.registry.register(Registry.LEAVE_EVENT, new LEAVE_EVENT_TYPE);
//add a new pin event
MY_NEW_BOT.registry.register(Registry.PIN_EVENT, new PIN_EVENT_TYPE);
```