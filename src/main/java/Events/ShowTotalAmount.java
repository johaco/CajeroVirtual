package Events;

import GUI.CashierWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTotalAmount implements ActionListener {
    private CashierWindow cashierWindow;

    public ShowTotalAmount(CashierWindow cashierWindow) {
        this.cashierWindow = cashierWindow;
        this.cashierWindow.getMontoTotalButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierWindow.getMontoTotalButton()) {
            // Muestra el monto total en la pantalla en función de la elección del usuario
            showTotalAmount();
        }
    }

    private void showTotalAmount() {

        double totalAmountInPesos = cashierWindow.getAuthenticatedCustomer().getTotalAmountInPesos();
        double totalAmountInDollars = cashierWindow.getAuthenticatedCustomer().getTotalAmountInDollars();

        // Muestra los montos totales en la pantalla
        String message = "Monto Total en Pesos: " + totalAmountInPesos + "\nMonto Total en Dólares: " + totalAmountInDollars;
        cashierWindow.getPantallaTextArea().setText(message);
    }
}
