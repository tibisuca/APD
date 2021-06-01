import java.awt.*; 
import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JFrame implements Runnable{
    public JPanel _MainPanel = new JPanel();
    public JLabel _UsersText = new JLabel("Number of users");
    public JTextArea _MessageBoard = new JTextArea();
    public JButton _StartButton = new JButton("Start");
    public JTextField _NumberOfUsers = new JTextField();
    public JLabel _Timer = new JLabel("Time elapsed: ");

    public GUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setVisible(true);

        _MainPanel.setPreferredSize(new Dimension(800, 600));

        _UsersText.setPreferredSize(new Dimension(100, 100));

        //_MessageBoard.setPreferredSize(new Dimension(500, 80000));
        _MessageBoard.setEnabled(true);
        _MessageBoard.setEditable(true);
        _MessageBoard.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(_MessageBoard, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(500, 500));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        scroll.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        _NumberOfUsers.setPreferredSize(new Dimension(50, 25));
        _NumberOfUsers.setDocument(new JTextFieldLimit(3));

        _Timer.setPreferredSize(new Dimension(600, 100));

        _StartButton.setPreferredSize(new Dimension(80, 25));
        
        _MainPanel.setBackground(Color.LIGHT_GRAY);
        _MainPanel.add(_UsersText, BorderLayout.EAST);
        _MainPanel.add(_NumberOfUsers, BorderLayout.AFTER_LAST_LINE);
        _MainPanel.add(_StartButton, BorderLayout.EAST);
        _MainPanel.add(scroll, BorderLayout.CENTER);
        //_MainPanel.add(_MessageBoard);
        _MainPanel.add(_Timer, BorderLayout.EAST);
        
        

        add(_MainPanel);
        
        

        pack();
        
    }

    public void Initialize()
    {
        this.add(_MessageBoard);
        this.add(_StartButton);
        this.add(_NumberOfUsers);
        this.add(_Timer);
    }


    @Override
    public void run() {
        while(true)
            repaint();
    }

}
