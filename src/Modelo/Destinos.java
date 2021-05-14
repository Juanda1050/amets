package Modelo;

public class Destinos {
    private int destinationID;
    private String city;
    private String state;
    private String country;

    public Destinos(){

    }

    public Destinos(int destinationID, String city, String state, String country)
    {
        this.destinationID = destinationID;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
