package wtf.wazed;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import wtf.wazed.Server.Client;
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
    public static Client client = new Client();
    private static ENVIORMENT enviorment = new ENVIORMENT();
    public Main(){
        SINGLETON = this;
        commandManager = new CommandManager();
    }

    public static void main(String[] args) throws LoginException {


        new Main();
        JDABuilder builder = JDABuilder.createDefault("Matthis kann nicht schreiben");
        builder.setToken(enviorment.getSecretKey());
        builder.setActivity(Activity.watching("Being a Better NSA"));
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
