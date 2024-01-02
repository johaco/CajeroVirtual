package GUI;

import Events.CreatClient;

import javax.swing.*;
import java.awt.*;

public class SignInWindow extends JFrame {
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField addressTextField;
    private JTextField birthDateTextField;
    private JTextField dniTextField;

    private JPasswordField passwordField1;
    private JButton registerButton;
    private JPanel SignInWindow;
    private JButton backButton;

    public SignInWindow() throws HeadlessException {
        setContentPane(SignInWindow);
        setSize(600,600);
        setLocationRelativeTo(null);
        // Instancia el evento y pasa la instancia actual de SignInWindow
        CreatClient creatClient = new CreatClient(this);
    }

    // Métodos getter para los componentes
    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getSurnameTextField() {
        return surnameTextField;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public JTextField getBirthDateTextField() {
        return birthDateTextField;
    }

    public JTextField getDniTextField() {
        return dniTextField;
    }

    public JPasswordField getPasswordField(){
        return passwordField1;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
