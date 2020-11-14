package wtf.wazed.command;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import wtf.wazed.Main;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class MessageEventHandler implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReceivedEvent) {
            MessageReceivedEvent event = (MessageReceivedEvent) genericEvent;
            if(!event.getAuthor().isBot()){
                Main.getInstance().getCommandManager().onMessage(event.getMessage().getContentRaw(), event);
            }
        }
    }
}
