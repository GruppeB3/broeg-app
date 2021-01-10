package models;

public class Brewer {

    private String name;
    private String macAddress;

    public Brewer(String name) {
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
