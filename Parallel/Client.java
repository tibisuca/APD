import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

class Client implements Runnable {
    public JTextArea _area;
    public int _clientId;
    public Client(JTextArea area, int clientId)
    {
        _clientId = clientId;
        _area = area;
    }
    public void connect(JTextArea area) throws ConnectException
    {
        try (Socket socket = new Socket("localhost", 1234)) {

            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

            BufferedReader in
                = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {
                    line = "request";

                    out.println(line);
                    out.flush();
                    String received = in.readLine();
                    if(received != null && received != "")
                    {
                        area.append("Client " + _clientId + ": " + received + "\n");
                        area.setCaretPosition(area.getText().length());
                    }
                    else
                        break;
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            connect(_area);
        } catch (ConnectException e) {
            System.out.println("Failed to connect to server: Connection refused");
        }
    }
}