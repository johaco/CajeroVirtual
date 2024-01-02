package Entyties;

import java.util.Objects;

public class Customer {
    private String name;
    private String surname;
    private String address;
    private int birthDay;
    private int monthBirth;
    private int yearBirth;
    private String password;
    private String DNI;
    private DebitCard debitCard;
    private int id;

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public Customer(String name, String surname, String address, int birthDay, int monthBirth, int yearBirth, String password, String DNI, DebitCard debitCard, int id) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDay = birthDay;
        this.monthBirth = monthBirth;
        this.yearBirth = yearBirth;
        this.password = password;
        this.DNI = DNI;
        this.debitCard = debitCard;
        this.id = id;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setMonthBirth(int monthBirth) {
        this.monthBirth = monthBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getMonthBirth() {
        return monthBirth;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPassword() {
        return password;
    }

    public String getDNI() {
        return DNI;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + surname);
        System.out.println("Dirección: " + address);
        System.out.println("Clave: " + password);
        System.out.println("Id: " + id);
    }

    public double getTotalAmountInPesos() {
        // Lógica para calcular el monto total en pesos
        // Puedes sumar el monto de la tarjeta y cualquier otro monto en pesos que pueda tener el cliente
        // Por ejemplo, puedes agregar lógica para sumar el monto de cuentas en pesos adicionales, si las tienes
        return debitCard != null ? debitCard.getPesoAmount() : 0; // Esto es solo un ejemplo, adapta según tus necesidades
    }

    public double getTotalAmountInDollars() {
        // Lógica para calcular el monto total en dólares
        // Similar a getTotalAmountInPesos, pero para dólares
        return debitCard != null ? debitCard.getDollarAmount() : 0; // Esto es solo un ejemplo, adapta según tus necesidades
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword());
    }
}
