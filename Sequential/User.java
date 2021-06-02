
import java.net.Socket;

import javax.swing.JTextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

public class User {
    public String _name;
    public int _id;
    public boolean isConnected;
    public Socket _clientSocket;
    public String _currentWord;

    public User(int id) {
        _id = id;
        _name = "User " + _id;
        isConnected = false;
    }

    public void establishConnection() throws IOException, UnknownHostException {

        _clientSocket = new Socket("localhost", 4999);
        isConnected = true;
    }

    public void requestWord(JTextArea area) {

        try {
            area.append(_name + " is requesting a word.\n");

            OutputStreamWriter out = new OutputStreamWriter(_clientSocket.getOutputStream());
            PrintWriter sendMessage = new PrintWriter(out);
            sendMessage.println("Request from " + _name);
            sendMessage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveWord(JTextArea area) {
        try {
            InputStreamReader in = new InputStreamReader(_clientSocket.getInputStream());
            BufferedReader received = new BufferedReader(in);
            _currentWord = received.readLine();
            area.append(_name + " received word " + _currentWord + "\n");
            _clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
