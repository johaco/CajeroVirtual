package Events;

import Entyties.Customer;
import Entyties.CustomerData;
import GUI.CashierWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactBook implements ActionListener {
    private CashierWindow cashierWindow;

    public ContactBook(CashierWindow cashierWindow) {
        this.cashierWindow = cashierWindow;
        this.cashierWindow.getAgendaDeContactosButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierWindow.getAgendaDeContactosButton()) {
            // Obtener la lista de clientes registrados
            java.util.List<Customer> customerList = CustomerData.getCustomerList();

            // Obtener el cliente autenticado
            Customer authenticatedCustomer = cashierWindow.getAuthenticatedCustomer();

            // Mostrar nombres y CBU de contactos externos en la pantalla
            StringBuilder contactos = new StringBuilder("Agenda de Contactos:\n");
            for (Customer customer : customerList) {
                // Excluir al cliente autenticado de la lista
                if (!customer.equals(authenticatedCustomer)) {
                    contactos.append("Nombre: ").append(customer.getName()).append("\tCBU: ").append(customer.getDebitCard().getCbu()).append("\n");
                }
            }

            // Actualizar la pantalla con la lista de contactos
            cashierWindow.getPantallaTextArea().setText(contactos.toString());
        }
    }
}
