package wtf.wazed.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import wtf.wazed.Main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public abstract class Command {

    protected static JDA jda;
    private String invoke = getClass().getAnnotation(Info.class).invoke();
    private String description = getClass().getAnnotation(Info.class).description();

    public Command() {
        jda = Main.getJda();
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    protected abstract void execute(String[] args, MessageReceivedEvent event);

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String invoke();

        String description() default "";
    }

}
