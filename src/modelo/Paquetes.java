package modelo;

public class Paquetes
{
    int ID;
    String name;
    String description;
    int passengers;
    int price;

    public Paquetes()
    {
    }

    public Paquetes(int ID, String name, String description, int passengers, int price) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.passengers = passengers;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
