package Events;

import Entyties.Customer;
import GUI.CashierWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowData implements ActionListener {
    private CashierWindow cashierWindow;

    public ShowData(CashierWindow cashierWindow) {
        this.cashierWindow = cashierWindow;
        this.cashierWindow.getDatosPersonalesButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierWindow.getDatosPersonalesButton()) {
            // Obtener el cliente autenticado desde la CashierWindow
            Customer authenticatedCustomer = cashierWindow.getAuthenticatedCustomer();

            if (authenticatedCustomer != null) {
                // Construir una cadena con los datos personales y de la tarjeta del cliente
                String datosPersonales = obtenerDatosPersonales(authenticatedCustomer);
                String datosTarjeta = obtenerDatosTarjeta(authenticatedCustomer);

                // Actualizar la pantalla con los datos personales y de la tarjeta
                cashierWindow.getPantallaTextArea().setText(datosPersonales + "\n\n" + datosTarjeta);
            }
        }
    }

    private String obtenerDatosPersonales(Customer customer) {
        // Construir una cadena con los datos personales del cliente
        return "Nombre: " + customer.getName() +
                "\nApellido: " + customer.getSurname() +
                "\nDNI: " + customer.getDNI() +
                "\nFecha de Nacimiento: " + customer.getBirthDay() + "/" + customer.getMonthBirth() + "/" + customer.getYearBirth() +
                "\nDirección: " + customer.getAddress();
    }

    private String obtenerDatosTarjeta(Customer customer) {
        // Obtener la información de la tarjeta del cliente
        if (customer.getDebitCard() != null) {
            return "\n\nDatos de la Tarjeta:" +
                    "\nNúmero de Tarjeta: " + customer.getDebitCard().getNumDebitCardAsString() +
                    "\nAlias de Banco: " + customer.getDebitCard().getAliasCard() +
                    "\nCBU: " + customer.getDebitCard().getCbu() +
                    "\nMonto en Dólares: " + customer.getDebitCard().getDollarAmount() +
                    "\nMonto en Pesos: " + customer.getDebitCard().getPesoAmount();
        } else {
            return "\n\nEl cliente no tiene una tarjeta asociada.";
        }
    }
}
