package wtf.wazed.audio;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.OpusPacket;
import net.dv8tion.jda.api.audio.UserAudio;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class GenericAudioReceiveHandler implements AudioReceiveHandler {
    private static final List<byte[]> BUFFER = new ArrayList<>();

    public static boolean getWavFile(String filePath, byte[] audioStream) throws IOException {

        boolean result = false;
        try {


            System.out.println(">> Decoded Data" + Arrays.toString(audioStream));
            File outFile = new File(filePath);
            AudioFormat format = new AudioFormat(8000, 16, 1, true, false);
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(audioStream), format, audioStream.length), AudioFileFormat.Type.WAVE, outFile);
            result = true;
            return result;
        } catch (IOException ex) {
            System.out.println("<< getWavFile - impl" + ex);
            return result;

        }
    }

    @Override
    public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
        byte[] bytes = combinedAudio.getAudioData(0.4f);
        BUFFER.add(bytes);
        List<Byte> byteList = new ArrayList<>();
        for (byte[] bytes1 : BUFFER) {
            for (byte b : bytes1) {
                byteList.add(b);
            }
        }
        try {
            Byte[] bytes1 = byteList.toArray(new Byte[0]);
            byte[] test = toPrimitives(bytes1);
            getWavFile("./test.wav", test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    byte[] toPrimitives(Byte[] oBytes)
    {

        byte[] bytes = new byte[oBytes.length];
        for(int i = 0; i < oBytes.length; i++){
            bytes[i] = oBytes[i];
        }
        return bytes;

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

    @Override
    public boolean canReceiveUser() {
        return true;
    }
}
