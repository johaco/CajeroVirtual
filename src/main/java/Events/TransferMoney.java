package Events;

import Entyties.Customer;
import Entyties.CustomerData;
import GUI.CashierWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMoney implements ActionListener {
    private CashierWindow cashierWindow;

    public TransferMoney(CashierWindow cashierWindow) {
        this.cashierWindow = cashierWindow;
        this.cashierWindow.getTransferenciaButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierWindow.getTransferenciaButton()) {
            // Obtener la lista de clientes registrados
            java.util.List<Customer> customerList = CustomerData.getCustomerList();

            // Obtener el cliente autenticado
            Customer authenticatedCustomer = cashierWindow.getAuthenticatedCustomer();

            // Filtrar la lista para excluir al cliente autenticado
            java.util.List<Customer> contactList = customerList.stream()
                    .filter(customer -> !customer.equals(authenticatedCustomer))
                    .toList();

            // Crear un arreglo de opciones para el diálogo de selección
            Object[] options = contactList.stream()
                    .map(Customer::getName)
                    .toArray();

            // Mostrar un diálogo de selección de contacto
            Object selectedContact = JOptionPane.showInputDialog(cashierWindow, "Seleccione el contacto para transferencia:",
                    "Transferencia de Dinero", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedContact != null) {
                // Obtener el contacto seleccionado
                Customer selectedCustomer = contactList.get(java.util.List.of(options).indexOf(selectedContact));

                // Mostrar un diálogo para ingresar el monto y la moneda
                JTextField montoField = new JTextField();
                JComboBox<String> monedaComboBox = new JComboBox<>(new String[]{"Dólares", "Pesos"});

                Object[] transferenciaDialog = {
                        "Monto:", montoField,
                        "Moneda:", monedaComboBox
                };

                int option = JOptionPane.showConfirmDialog(cashierWindow, transferenciaDialog, "Transferencia de Dinero", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    try {
                        // Obtener el monto y la moneda ingresados
                        double monto = Double.parseDouble(montoField.getText());
                        String moneda = (String) monedaComboBox.getSelectedItem();

                        // Realizar la transferencia
                        boolean transferenciaExitosa = transferirDinero(authenticatedCustomer, selectedCustomer, monto, moneda);

                        // Mostrar un mensaje de éxito o error según el resultado de la transferencia
                        if (transferenciaExitosa) {
                            JOptionPane.showMessageDialog(cashierWindow, "Transferencia exitosa a " + selectedCustomer.getName() +
                                    " por " + monto + " " + moneda, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(cashierWindow, "Fondos insuficientes o error en la transferencia",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        // Manejar la entrada inválida del monto
                        JOptionPane.showMessageDialog(cashierWindow, "Ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private boolean transferirDinero(Customer remitente, Customer destinatario, double monto, String moneda) {
        // Verificar que el remitente y destinatario no sean el mismo cliente
        if (!remitente.equals(destinatario)) {
            // Verificar si el remitente tiene fondos suficientes
            if (moneda.equals("Dólares") && remitente.getDebitCard().getDollarAmount() >= monto ||
                    moneda.equals("Pesos") && remitente.getDebitCard().getPesoAmount() >= monto) {
                // Descontar el monto de la cuenta del remitente
                if (moneda.equals("Dólares")) {
                    remitente.getDebitCard().setDollarAmount(remitente.getDebitCard().getDollarAmount() - monto);
                } else {
                    remitente.getDebitCard().setPesoAmount(remitente.getDebitCard().getPesoAmount() - monto);
                }

                // Añadir el monto a la cuenta del destinatario
                if (moneda.equals("Dólares")) {
                    destinatario.getDebitCard().setDollarAmount(destinatario.getDebitCard().getDollarAmount() + monto);
                } else {
                    destinatario.getDebitCard().setPesoAmount(destinatario.getDebitCard().getPesoAmount() + monto);
                }

                return true; // La transferencia fue exitosa
            } else {
                return false; // Fondos insuficientes
            }
        } else {
            // No permitir transferencia a sí mismo
            JOptionPane.showMessageDialog(cashierWindow, "No puedes transferir a tu propia cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
