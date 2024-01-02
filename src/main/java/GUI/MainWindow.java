package GUI;

import javax.swing.*;

public class MainWindow extends JFrame{
    private JPanel MainPanel;
    private JButton INICIARSESIONButton;
    private JButton REGISTRARSEButton;
    private JLabel MainTitle;

    LogInWindow logInWindow;
    SignInWindow signInWindow;

    public MainWindow(){
        setContentPane(MainPanel);
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        REGISTRARSEButton.addActionListener(e -> {
            signInWindow = new SignInWindow();
            signInWindow.setVisible(true);
        });
        INICIARSESIONButton.addActionListener(e -> {
            logInWindow = new LogInWindow();
            logInWindow.setVisible(true);
        });
    }
}
