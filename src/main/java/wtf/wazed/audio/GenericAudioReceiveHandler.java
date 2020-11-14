package wtf.wazed.audio;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.OpusPacket;
import net.dv8tion.jda.api.audio.UserAudio;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class GenericAudioReceiveHandler implements AudioReceiveHandler {
    @Override
    public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
        byte[] bytes = combinedAudio.getAudioData(0.4f);
        System.out.println(Arrays.toString(bytes));
    }

    @Override
    public void handleEncodedAudio(@NotNull OpusPacket packet) {

    }

    @Override
    public void handleUserAudio(@NotNull UserAudio userAudio) {
       byte[] bytes = userAudio.getAudioData(0.4f);
        System.out.println(userAudio.getUser().getName());

    }

    @Override
    public boolean canReceiveCombined() {
        return true;
    }
}
