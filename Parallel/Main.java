import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        

        GUI gui = new GUI();
        gui.setTitle("Parallel");
        Timer timer = new Timer(gui._Timer);

        gui._StartButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String _noUsers = gui._NumberOfUsers.getText();
                boolean ok = true;
                for(int i = 0; i<_noUsers.length(); i++)
                    if(Character.isLetter(_noUsers.charAt(i)))
                    {
                        ok = false;
                        break;
                    }
                if(ok == true)
                {
                    ExecutorService timerExecutor = Executors.newSingleThreadExecutor();
                    timerExecutor.execute(timer);
                    int noUsers = Integer.parseInt(_noUsers);

                    Client[] clients = new Client[noUsers];
                    ExecutorService pool = Executors.newFixedThreadPool(noUsers); 

                    for(int i =0; i<noUsers; i++) {
                        clients[i] = new Client(gui._MessageBoard, i);
                    }

                    try{
                        for (Client client : clients) {
                            pool.execute(client);
                        }
                        pool.shutdown();
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    
}
