import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<User>();
        GUI gui = new GUI();

        gui.startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int noOfUsers;
                int noOfRequests;
                noOfUsers = Integer.parseInt(gui.usersTextField.getText());
                noOfRequests = Integer.parseInt(gui.requestsTextField.getText());
                for (int i = 0; i < noOfUsers; i++)
                    users.add(new User(i));

                ConnectionManager connectionManager = new ConnectionManager(noOfUsers, users);

                try {
                    for (int i = 0; i < noOfRequests; i++) {
                        connectionManager.connectUsers();
                        connectionManager.requestWords(gui.messageBoard);
                        connectionManager.receiveWords(gui.messageBoard);
                    }
                } catch (UnknownHostException ex) {
                    System.out.println("Connection to server failed");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

    }
}
