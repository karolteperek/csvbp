package pl.karolteperek;

import javafx.beans.property.SimpleStringProperty;

public class ArrayToProperty {

    private SimpleStringProperty operationNO; // Numer operacji [0]
    private SimpleStringProperty operation; // Data operacji [1]
    private SimpleStringProperty currency; // Data waluty [2]
    private SimpleStringProperty transaction; // Typ transakcji [3]
    private SimpleStringProperty amount; // Kwota [4]
    private SimpleStringProperty description; // Opis [5]
    private SimpleStringProperty address; // Adres [6]
    private SimpleStringProperty address_second; // Adres [7]

    public ArrayToProperty(String operationNO, String operation, String currency, String transaction, String amount,
                           String description, String address, String address_second) {
        this.operationNO = new SimpleStringProperty(operationNO);
        this.operation = new SimpleStringProperty(operation);
        this.currency = new SimpleStringProperty(currency);
        this.transaction = new SimpleStringProperty(transaction);
        this.amount = new SimpleStringProperty(amount);
        this.description = new SimpleStringProperty(description);
        this.address = new SimpleStringProperty(address);
        this.address_second = new SimpleStringProperty(address_second);
    }

    public ArrayToProperty(String[] strings) {
        this.operationNO = new SimpleStringProperty(strings[0]);
        this.operation = new SimpleStringProperty(strings[1]);
        this.currency = new SimpleStringProperty(strings[2]);
        this.transaction = new SimpleStringProperty(strings[3]);
        this.amount = new SimpleStringProperty(strings[4]);
        this.description = new SimpleStringProperty(strings[5]);
        this.address = new SimpleStringProperty(strings[6]);
        this.address_second = new SimpleStringProperty((strings[7]));
    }

    public String getOperationNO() {
        return operationNO.get();
    }

    public SimpleStringProperty operationNOProperty() {
        return operationNO;
    }

    public String getOperation() {
        return operation.get();
    }

    public SimpleStringProperty operationProperty() {
        return operation;
    }

    public String getCurrency() {
        return currency.get();
    }

    public SimpleStringProperty currencyProperty() {
        return currency;
    }

    public String getTransaction() {
        return transaction.get();
    }

    public SimpleStringProperty transactionProperty() {
        return transaction;
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getAddress_second() {
        return address_second.get();
    }

    public SimpleStringProperty address_secondProperty() {
        return address_second;
    }

    public void setAddress_second(String address_second) {
        this.address_second.set(address_second);
    }
}
