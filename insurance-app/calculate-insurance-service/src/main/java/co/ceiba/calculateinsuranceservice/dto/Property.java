package co.ceiba.calculateinsuranceservice.dto;

public class Property {

    private String address;
    private int stratum;
    private long value;
    private TypeProperty type;

    public Property() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStratum() {
        return stratum;
    }

    public void setStratum(int stratum) {
        this.stratum = stratum;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public TypeProperty getType() {
        return type;
    }

    public void setType(TypeProperty type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Property{" +
                "address='" + address + '\'' +
                ", stratum=" + stratum +
                ", value=" + value +
                ", type=" + type +
                '}';
    }
}

enum TypeProperty {
    HOUSE, APARTMENT, LOCAL_SHOP;
}