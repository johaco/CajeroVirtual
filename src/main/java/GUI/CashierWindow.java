package GUI;

import Entyties.Customer;
import Events.*;

import javax.swing.*;

public class CashierWindow extends JFrame {
    private JPanel CashierWindow;
    private JTextArea pantallaTextArea;
    private JButton datosPersonalesButton;
    private JButton montoTotalButton;
    private JButton depositarDineroButton;
    private JButton retirarDineroButton;
    private JButton transferenciaButton;
    private JButton agendaDeContactosButton;
    private JButton salirButton;

    private Customer authenticatedCustomer;

    public CashierWindow(Customer authenticatedCustomer) {
        this.authenticatedCustomer = authenticatedCustomer;
        setContentPane(CashierWindow);
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShowData showData = new ShowData(this);
        DepositMoney depositMoney = new DepositMoney(this);
        WithdrawMoney withdrawMoney = new WithdrawMoney(this);
        ContactBook contactBook = new ContactBook(this);
        TransferMoney transferMoney = new TransferMoney(this);
        ShowTotalAmount showTotalAmount = new ShowTotalAmount(this);



        // Agregar ActionListener al botón "Salir"
        salirButton.addActionListener(e -> {
            // Cerrar la ventana actual
            dispose();

            // Abrir la ventana principal (MainWindow)
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }

    public JTextArea getPantallaTextArea() {
        return pantallaTextArea;
    }

    public Customer getAuthenticatedCustomer() {
        return authenticatedCustomer;
    }

    // Otros métodos getter para los demás botones, si es necesario
    public JButton getDatosPersonalesButton() {
        return datosPersonalesButton;
    }

    public JButton getDepositarDineroButton() {
        return depositarDineroButton;
    }

    public JButton getRetirarDineroButton() {
        return retirarDineroButton;
    }

    public JButton getAgendaDeContactosButton() {
        return agendaDeContactosButton;
    }

    public JButton getTransferenciaButton() {
        return transferenciaButton;
    }

    public JButton getMontoTotalButton() {
        return montoTotalButton;
    }

}
