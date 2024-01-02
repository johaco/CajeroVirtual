package GUI;

import Events.VerifyClient;

import javax.swing.*;

public class LogInWindow extends JFrame {
    private JPanel LogInWindow;
    private JTextField dniTextField;
    private JPasswordField passwordField;
    private JButton ACEPTARButton;

    public LogInWindow() {
        setContentPane(LogInWindow);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Instancia el evento y pasa la instancia actual de LogInWindow
        VerifyClient verifyClient = new VerifyClient(this);
    }

    public JTextField getDniTextField() {
        return dniTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getACEPTARButton() {
        return ACEPTARButton;
    }
}
