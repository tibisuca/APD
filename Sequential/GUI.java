import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;



public class GUI extends JFrame{
    public JLabel usersLabel;
    public JLabel requestsLabel;
    
    public JTextField usersTextField;
    public JTextField requestsTextField;

    public JTextArea messageBoard;
    public JButton startButton;
    
    public GUI()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800, 600));

        usersLabel = new JLabel("Number of users");
        usersLabel.setPreferredSize(new Dimension(150, 50));

        usersTextField = new JTextField();
        usersTextField.setPreferredSize(new Dimension(50, 25));

        requestsTextField = new JTextField();
        requestsTextField.setPreferredSize(new Dimension(50, 25));

        requestsLabel = new JLabel("Number of requests");
        requestsLabel.setPreferredSize(new Dimension(150, 25));

        messageBoard = new JTextArea();
        messageBoard.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messageBoard, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        startButton = new JButton("Start");
        
        mainPanel.add(usersLabel, BorderLayout.EAST);
        mainPanel.add(usersTextField, BorderLayout.EAST);
        mainPanel.add(startButton, BorderLayout.AFTER_LAST_LINE);

        mainPanel.add(requestsLabel, BorderLayout.WEST);
        mainPanel.add(requestsTextField, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }
}
