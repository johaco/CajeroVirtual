package Events;

import Entyties.Customer;
import GUI.CashierWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositMoney implements ActionListener {
    private CashierWindow cashierWindow;

    public DepositMoney(CashierWindow cashierWindow) {
        this.cashierWindow = cashierWindow;
        this.cashierWindow.getDepositarDineroButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierWindow.getDepositarDineroButton()) {
            // Obtener el cliente autenticado desde la CashierWindow
            Customer authenticatedCustomer = cashierWindow.getAuthenticatedCustomer();

            if (authenticatedCustomer != null) {
                // Solicitar la moneda en la que se desea depositar
                Object[] options = {"Dólares", "Pesos"};
                int monedaSeleccionada = JOptionPane.showOptionDialog(
                        cashierWindow,
                        "Seleccione la moneda en la que desea depositar:",
                        "Depositar Dinero",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (monedaSeleccionada == JOptionPane.CLOSED_OPTION) {
                    // El usuario cerró la ventana sin seleccionar una opción
                    return;
                }

                // Solicitar el monto a depositar mediante una ventana emergente
                String montoStr = JOptionPane.showInputDialog(cashierWindow, "Ingrese el monto a depositar:", "Depositar Dinero", JOptionPane.QUESTION_MESSAGE);

                try {
                    // Convertir la entrada a un valor numérico
                    double montoDepositado = Double.parseDouble(montoStr);

                    // Validar que el monto sea positivo
                    if (montoDepositado > 0) {
                        // Realizar el depósito
                        depositarDinero(authenticatedCustomer, montoDepositado, monedaSeleccionada);

                        // Actualizar la pantalla con el nuevo saldo
                        actualizarPantalla(authenticatedCustomer, monedaSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(cashierWindow, "Ingrese un monto válido (mayor que cero).", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(cashierWindow, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void depositarDinero(Customer customer, double monto, int monedaSeleccionada) {
        // Realizar la lógica de depósito aquí
        // Puedes actualizar el saldo en la tarjeta del cliente u otro objeto según tu diseño
        // Ejemplo:
        if (monedaSeleccionada == 0) {
            customer.getDebitCard().setDollarAmount(customer.getDebitCard().getDollarAmount() + monto);
        } else {
            customer.getDebitCard().setPesoAmount(customer.getDebitCard().getPesoAmount() + monto);
        }
    }

    private void actualizarPantalla(Customer customer, int monedaSeleccionada) {
        // Actualizar la pantalla con el nuevo estado después del depósito
        // Puedes mostrar el nuevo saldo en dólares o pesos según la moneda seleccionada
        if (monedaSeleccionada == 0) {
            cashierWindow.getPantallaTextArea().setText("Nuevo Saldo en Dólares: " + customer.getDebitCard().getDollarAmount());
        } else {
            cashierWindow.getPantallaTextArea().setText("Nuevo Saldo en Pesos: " + customer.getDebitCard().getPesoAmount());
        }
    }
}
