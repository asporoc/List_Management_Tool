package belegaufgabe;

public class item {
    int serialNumber;
    double price;
    String name;
    String category;
    public item(int serialNumber, double price, String name, String category){
        this.serialNumber=serialNumber;
        this.price =price;
        this.name=name;
        this.category=category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }


}
