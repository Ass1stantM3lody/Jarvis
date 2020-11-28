package wtf.wazed.audio;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.OpusPacket;
import net.dv8tion.jda.api.audio.UserAudio;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import wtf.wazed.Main;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wazed
 * Created on 14.11.2020
 */
public class GenericAudioReceiveHandler implements AudioReceiveHandler {
    private static final List<byte[]> BUFFER = new ArrayList<>();

    public static boolean getWavFile(String filePath, byte[] audioStream) throws IOException {
        File outFile = new File(filePath);
        AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(audioStream), OUTPUT_FORMAT, audioStream.length), AudioFileFormat.Type.WAVE, outFile);
        return true;
    }

    @Override
    public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
        System.out.println("Handling Combined audio");
        byte[] bytes = combinedAudio.getAudioData(0.4f);

    }
    public byte[] getAudio(byte[] bytes){
         BUFFER.add(bytes);
        List<Byte> byteList = new ArrayList<>();
        for (byte[] bytes1 : BUFFER) {
            for (byte b : bytes1) {
                byteList.add(b);
            }
        } Byte[] bytes1 = byteList.toArray(new Byte[0]);
            byte[] test = toPrimitives(bytes1);
            return test;
    }


    private void safeToBufferAndCreateFile(byte[] bytes){
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

    private void safeToBufferAndCreateUserFile(byte[] bytes, User user){
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
            getWavFile("./test-" + user.getName() + ".wav", test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    byte[] toPrimitives(Byte[] oBytes) {

        byte[] bytes = new byte[oBytes.length];
        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;

    }


    @Override
    public void handleEncodedAudio(@NotNull OpusPacket packet) {

    }

    @Override
    public void handleUserAudio(@NotNull UserAudio userAudio) {
        if(userAudio.getUser().isBot())return;
        byte[] bytes = userAudio.getAudioData(0.4f);
        try {
            Main.client.handleByteArray(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
