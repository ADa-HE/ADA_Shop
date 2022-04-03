package entity;

/**
 *
 * @author MT Bac Ninh
 */
public class Shippers {

    private int ShipperID; //auto number
    private String CompanyName, Phone;

    public Shippers(String CompanyName, String Phone) {
        this.CompanyName = CompanyName;
        this.Phone = Phone;
    }

    public Shippers() {
    }

    public Shippers(int ShipperID, String CompanyName, String Phone) {
        this.ShipperID = ShipperID;
        this.CompanyName = CompanyName;
        this.Phone = Phone;
    }

    public int getShipperID() {
        return ShipperID;
    }

    public void setShipperID(int ShipperID) {
        this.ShipperID = ShipperID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

}
