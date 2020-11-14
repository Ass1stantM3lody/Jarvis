package wtf.wazed.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import sun.plugin2.message.Message;
import wtf.wazed.command.impl.ConnectCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class CommandManager {

    private final static List<Command> commands = new ArrayList<>();

    public CommandManager(){
        addCommand(new ConnectCommand());
    }

    public Command getCommand(String invoke){
        return commands.stream().filter(iCommand -> iCommand.getInvoke().equals(invoke)).findFirst().orElse(null);
    }

    public void addCommand(Command command){
        getCommands().add(command);
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public void onMessage(String message, MessageReceivedEvent event){
        if(message.startsWith(".")) {
            String[] messageSplit = message.split(" ");
            String[] args = Arrays.copyOfRange(messageSplit, 1, messageSplit.length);
            String invoke = messageSplit[0].substring(1);
            commands.stream().filter(command -> command.getInvoke().equals(invoke)).forEach(command -> command.execute(args,event));
        }

    }

}
