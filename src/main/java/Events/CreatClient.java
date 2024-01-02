package Events;

import Entyties.Customer;
import Entyties.CustomerData;
import Entyties.DebitCard;
import GUI.MainWindow;
import GUI.SignInWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class CreatClient implements ActionListener {
    private SignInWindow signInWindow;

    public CreatClient(SignInWindow signInWindow) {
        this.signInWindow = signInWindow;
        signInWindow.getRegisterButton().addActionListener(this);
        signInWindow.getBackButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInWindow.getRegisterButton()) {
            // Obtener datos del formulario
            String name = signInWindow.getNameTextField().getText();
            String surname = signInWindow.getSurnameTextField().getText();
            String address = signInWindow.getAddressTextField().getText();

            // Obtener la fecha completa del TextField
            String birthDate = signInWindow.getBirthDateTextField().getText();
            String[] dateParts = birthDate.split("/");

            // Obtener la password del JPasswordField
            char[] passwordChars = signInWindow.getPasswordField().getPassword();
            // Convertir el array de caracteres a una cadena
            String password = new String(passwordChars);

            // Asegurarse de que haya tres partes (día, mes, año)
            if (dateParts.length == 3) {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                // Calcular la edad
                int edadMinima = 18;
                int edad = calculateAge(year, month, day);

                // Verificar si el usuario es mayor de edad
                if (edad < edadMinima) {
                    // El usuario no es mayor de edad
                    JOptionPane.showMessageDialog(signInWindow, "Debes ser mayor de edad para registrarte", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // No continúes con el registro
                }

                // Obtener el resto de los campos
                String dni = signInWindow.getDniTextField().getText();

                // Crear una nueva instancia de DebitCard y asignar valores iniciales
                DebitCard debitCard = new DebitCard();
                debitCard.debitCardGenerator();
                debitCard.generateAliasCard();
                debitCard.generateCBU();

                // Crear una nueva instancia de Customer con los datos del formulario y la tarjeta
                Customer newCustomer = new Customer(name, surname, address, day, month, year, password, dni, debitCard, 0);

                // Agregar el nuevo cliente a la lista
                CustomerData.addCustomer(newCustomer);

                // Muestra un mensaje de éxito o realiza otras acciones según sea necesario
                System.out.println("Cliente registrado con éxito");
                // Mostrar un mensaje de éxito
                JOptionPane.showMessageDialog(signInWindow, "Cliente registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                signInWindow.dispose();

                // Abrir la ventana anterior (MainWindow)
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        } else if (e.getSource() == signInWindow.getBackButton()) {
            // Cerrar la ventana actual
            signInWindow.dispose();

            // Abrir la ventana anterior (MainWindow)
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        } else {
            // Manejar el caso en el que la fecha no esté en el formato esperado
            System.err.println("Error en el formato de la fecha");
        }
    }

    private int calculateAge(int birthYear, int birthMonth, int birthDay) {
        LocalDate birthdate = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }
}
