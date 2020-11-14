package wtf.wazed;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import wtf.wazed.audio.GenericAudioHandler;
import wtf.wazed.command.CommandManager;
import wtf.wazed.command.MessageEventHandler;

import javax.security.auth.login.LoginException;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class Main {
    private static JDA jda;
    private CommandManager commandManager;
    private static Main SINGLETON;
    public Main(){
        SINGLETON = this;
        commandManager = new CommandManager();
    }

    public static void main(String[] args) throws LoginException {
        new Main();
        JDABuilder builder = JDABuilder.createDefault("Du dumme Snitch nutte");
        builder.setActivity(Activity.watching("Jona verkacken lmao"));
        builder.addEventListeners(new MessageEventHandler());
        jda = builder.build();



    }

    public static Main getInstance() {
        return SINGLETON;
    }

    public static JDA getJda() {
        return jda;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
