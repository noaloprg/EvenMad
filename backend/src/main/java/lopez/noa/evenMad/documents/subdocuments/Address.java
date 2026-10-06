package lopez.noa.evenMad.documents.subdocuments;

public class Address {
    private String street;
    private String city;
    private int postalCode;
    private int number;

    public Address() {
    }

    public Address(String street, String city, int postalCode, int number) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
