package Events;

import Entyties.Customer;
import GUI.CashierWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawMoney implements ActionListener {
    private CashierWindow cashierWindow;

    public WithdrawMoney(CashierWindow cashierWindow) {
        this.cashierWindow = cashierWindow;
        this.cashierWindow.getRetirarDineroButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierWindow.getRetirarDineroButton()) {
            // Obtener el cliente autenticado desde la CashierWindow
            Customer authenticatedCustomer = cashierWindow.getAuthenticatedCustomer();

            if (authenticatedCustomer != null) {
                // Solicitar la moneda en la que se desea retirar
                Object[] options = {"Dólares", "Pesos"};
                int monedaSeleccionada = JOptionPane.showOptionDialog(
                        cashierWindow,
                        "Seleccione la moneda en la que desea retirar:",
                        "Retirar Dinero",
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

                // Solicitar el monto a retirar mediante una ventana emergente
                String montoStr = JOptionPane.showInputDialog(cashierWindow, "Ingrese el monto a retirar:", "Retirar Dinero", JOptionPane.QUESTION_MESSAGE);

                try {
                    // Convertir la entrada a un valor numérico
                    double montoRetirado = Double.parseDouble(montoStr);

                    // Validar que el monto sea positivo
                    if (montoRetirado > 0) {
                        // Realizar el retiro
                        retirarDinero(authenticatedCustomer, montoRetirado, monedaSeleccionada);

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

    private void retirarDinero(Customer customer, double monto, int monedaSeleccionada) {
        // Realizar la lógica de retiro aquí
        // Puedes actualizar el saldo en la tarjeta del cliente u otro objeto según tu diseño
        // Ejemplo:
        if (monedaSeleccionada == 0) {
            double saldoDolares = customer.getDebitCard().getDollarAmount();
            if (saldoDolares >= monto) {
                customer.getDebitCard().setDollarAmount(saldoDolares - monto);
            } else {
                JOptionPane.showMessageDialog(cashierWindow, "Saldo insuficiente en dólares.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            double saldoPesos = customer.getDebitCard().getPesoAmount();
            if (saldoPesos >= monto) {
                customer.getDebitCard().setPesoAmount(saldoPesos - monto);
            } else {
                JOptionPane.showMessageDialog(cashierWindow, "Saldo insuficiente en pesos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarPantalla(Customer customer, int monedaSeleccionada) {
        // Actualizar la pantalla con el nuevo estado después del retiro
        // Puedes mostrar el nuevo saldo en dólares o pesos según la moneda seleccionada
        if (monedaSeleccionada == 0) {
            cashierWindow.getPantallaTextArea().setText("Nuevo Saldo en Dólares: " + customer.getDebitCard().getDollarAmount());
        } else {
            cashierWindow.getPantallaTextArea().setText("Nuevo Saldo en Pesos: " + customer.getDebitCard().getPesoAmount());
        }
    }
}
