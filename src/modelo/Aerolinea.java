package modelo;

public class Aerolinea {
    private int airlineID;
    private String airlineName;
    private String flyClass;
    private double price;

    public Aerolinea(){
    }

    public int getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlyClass() {
        return flyClass;
    }

    public void setFlyClass(String flyClass) {
        this.flyClass = flyClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
