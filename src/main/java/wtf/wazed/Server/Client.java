package wtf.wazed.Server;

import wtf.wazed.audio.GenericAudioReceiveHandler;

import javax.sound.sampled.AudioInputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    Socket socket;
    public Client(){

    }

    public void handleByteArray(byte[] bytes) throws IOException {
        socket.getOutputStream().write(bytes);
      socket.getOutputStream().flush();
    }
    private void connect() throws IOException {
        Socket socket = new Socket("localhost", 7777);

    }
}
