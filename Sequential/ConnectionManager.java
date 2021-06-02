
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class ConnectionManager {
    
    public ArrayList<User> _users = new ArrayList<User>();
    public int _noOfUsers;

    public ConnectionManager(int noOfUsers, ArrayList<User> users) {
        _noOfUsers = noOfUsers;
        _users = users;
    }

    public void connectUsers() throws IOException, UnknownHostException 
    {
        for(int i = 0; i < _noOfUsers; i++)
        {
            try {
                _users.get(i).establishConnection();
            } catch (UnknownHostException e) {
                System.out.println("Host not found");
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }
    
    public void requestWords(JTextArea area)
    {
        for(int i = 0; i < _noOfUsers; i++)
        {
            if(_users.get(i).isConnected)
            {
                _users.get(i).requestWord(area);
            }
        }
    }

    public void receiveWords(JTextArea area)
    {
        for(int i = 0; i < _noOfUsers; i++)
        {
            if(_users.get(i).isConnected)
            {
                _users.get(i).receiveWord(area);
            }
        }
    }
}
