package Events;

import Entyties.Customer;
import Entyties.CustomerData;
import GUI.CashierWindow;
import GUI.LogInWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerifyClient implements ActionListener {
    private LogInWindow logInWindow;

    public VerifyClient(LogInWindow logInWindow) {
        this.logInWindow = logInWindow;
        logInWindow.getACEPTARButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logInWindow.getACEPTARButton()) {
            // Obtener datos del formulario
            String dni = logInWindow.getDniTextField().getText();

            // Obtener la password del JPasswordField
            char[] passwordChars = logInWindow.getPasswordField().getPassword();
            // Convertir el array de caracteres a una cadena
            String password = new String(passwordChars);

            // Verificar las credenciales
            Customer authenticatedCustomer = authenticateCustomer(dni, password);

            if (authenticatedCustomer != null) {
                // Las credenciales son correctas
                // Abre la ventana CashierWindow con el cliente autenticado
                CashierWindow cashierWindow = new CashierWindow(authenticatedCustomer);
                cashierWindow.setVisible(true);

                // Cierra la ventana actual
                logInWindow.dispose();
            } else {
                // Las credenciales son incorrectas
                JOptionPane.showMessageDialog(logInWindow, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Customer authenticateCustomer(String dni, String password) {
        // Buscar al cliente por DNI y verificar la contraseña
        for (Customer customer : CustomerData.getCustomerList()) {
            if (customer.getDNI().equals(dni) && customer.getPassword().equals(password)) {
                return customer; // Cliente autenticado
            }
        }
        return null; // Cliente no encontrado o credenciales incorrectas
    }
}
