
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Server {
    public static List<String> words = new ArrayList<String>();
    public static int Length;
    public static Socket _currentClient;

    public Server() {
        Length = 0;
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException
    {
        seedData();
        System.out.println(Length);
        ServerSocket server = new ServerSocket(4999);
        System.out.println("Server started\n");
        while(Length > 0)
        {
            try {
                _currentClient = server.accept();
                receiveRequest();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        server.close();
    }

    private static void seedData()
    {
        File dictionary = new File("dictionary.txt");
        try {
            Scanner in = new Scanner(dictionary);
            while(in.hasNextLine())
            {
                words.add(in.nextLine());
                Length++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dctionary not found");
        }
    }

    private static void receiveRequest()
    {
        try {
            InputStreamReader request = new InputStreamReader(_currentClient.getInputStream());
            BufferedReader received = new BufferedReader(request);
            String receivedRequest = received.readLine();
            if(receivedRequest.contains("Request")){
                System.out.println(receivedRequest);
                sendWord();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendWord()
    {
        try {
            OutputStreamWriter word = new OutputStreamWriter(_currentClient.getOutputStream());
            PrintWriter pr = new PrintWriter(word);
            pr.println(words.remove(0));
            pr.flush();
        } catch (IOException e) {
            System.out.println("Could not send word to client");
            e.printStackTrace();
        }
    }
}
