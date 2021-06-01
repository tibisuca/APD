import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
  
// Server class
class Server {
    public static List<String> words = new ArrayList<String>();
    public static int length;
    public static void main(String[] args)
    {
        ServerSocket server = null;
  
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
            length = seedValues();
            while (words.size() > 0) {
                Socket client = server.accept();
                ClientHandler clientSock
                    = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int seedValues()
    {
        int counter = 0;
        try {
            File dictionary = new File("dictionary.txt");
            Scanner scanner = new Scanner(dictionary);
            while (scanner.hasNextLine()) {
              String word = scanner.nextLine();
              words.add(word);
              counter++;
            }
            System.out.println(counter);
            scanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          return counter;
    }
  
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
  
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {

                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
                //String line;
                while (words.size() > 0) {
                    System.out.println(words.size());
                    //System.out.printf("Received command from user: %s\n", line);
                    out.println("Received word:" + words.remove(0) + " | Words left: " + words.size());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}