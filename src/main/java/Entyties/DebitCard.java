package Entyties;

import java.util.Random;

public class DebitCard {
    private int id;
    private int[] numDebitCard = new int[16];
    private double pesoAmount;
    private double dollarAmount;
    private String aliasCard;
    private String cbu;
    private Random random = new Random();

    public DebitCard() {
    }

    public DebitCard(int id, double monto_peso, double monto_dolar, int[] numDebitCard, String aliasBanco, String cbu) {
        this.id = id;
        this.pesoAmount = monto_peso;
        this.dollarAmount = monto_dolar;
        this.numDebitCard = numDebitCard;
        this.aliasCard = aliasBanco;
        this.cbu = cbu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPesoAmount() {
        return pesoAmount;
    }

    public void setPesoAmount(double pesoAmount) {
        this.pesoAmount = pesoAmount;
    }

    public double getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(double dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    public String getAliasCard() {
        return aliasCard;
    }

    public void setAliasCard(String aliasCard) {
        this.aliasCard = aliasCard;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }



    public void debitCardGenerator() {
        for (int i = 0; i < numDebitCard.length; i++) {
            numDebitCard[i] = random.nextInt(10);
        }
    }

    public void generateAliasCard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            builder.append(randomChar);
        }
        aliasCard = builder.toString();
    }

    public void generateCBU() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            int randomDigit = random.nextInt(10);
            builder.append(randomDigit);
        }
        cbu = builder.toString();
    }

    public void showCardData() {
        System.out.println("ID: " + id);
        System.out.println("Monto en dólares: " + dollarAmount);
        System.out.println("Monto en pesos: " + pesoAmount);
        System.out.println("CBU: " + cbu);
        System.out.println("Alias de Banco: " + aliasCard);
        System.out.print("Número de tarjeta: ");
        for (int i = 0; i < numDebitCard.length; i++) {
            System.out.print(numDebitCard[i]);
        }
        System.out.println();
    }
    public String getNumDebitCardAsString() {
        StringBuilder builder = new StringBuilder();
        for (int num : numDebitCard) {
            builder.append(num);
        }
        return builder.toString();
    }

}
