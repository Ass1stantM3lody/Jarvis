package wtf.wazed.command.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.internal.entities.GuildImpl;
import wtf.wazed.Main;
import wtf.wazed.audio.GenericAudioHandler;
import wtf.wazed.audio.GenericAudioReceiveHandler;
import wtf.wazed.command.Command;

/**
 * @author Wazed
 * Created on 14.11.2020
 */

@Command.Info(invoke = "connect")
public class ConnectCommand extends Command {

    @Override
    protected void execute(String[] args, MessageReceivedEvent event) {
        User author = event.getAuthor();
        Member member = event.getMember();
        VoiceChannel channel;
        if((channel = member.getVoiceState().getChannel()) != null){
            Main.getJda().getDirectAudioController().connect(channel);
            AudioManager handler = member.getVoiceState().getGuild().getAudioManager();
            handler.openAudioConnection(channel);
            handler.setReceivingHandler(new GenericAudioReceiveHandler());
        }
    }
}
