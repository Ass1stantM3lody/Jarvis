package wtf.wazed.audio;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.internal.audio.AudioConnection;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.managers.AudioManagerImpl;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class GenericAudioHandler extends AudioManagerImpl {
    public GenericAudioHandler(GuildImpl guild) {
        super(guild);
    }

    @Override
    public AudioConnection getAudioConnection() {
        return super.getAudioConnection();
    }

    @Override
    public AudioReceiveHandler getReceivingHandler() {
        return super.getReceivingHandler();
    }

    @Override
    public void setConnectedChannel(VoiceChannel channel) {
        super.setConnectedChannel(channel);
    }
}
