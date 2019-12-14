package sample;

public class Products {

    private String Pname;
    private String Quantity;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    private  String des;
    private Double oldprice;
    private Double newprice;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Products(String pname, String quantity,String des, Double oldprice, Double newprice, String image) {
        Pname = pname;
        Quantity = quantity;
        this.des=des;
        this.oldprice = oldprice;
        this.newprice = newprice;
        this.image = image;
    }

    private String image;




    public void setPname(String pname) {
        Pname = pname;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public void setOldprice(Double oldprice) {
        this.oldprice = oldprice;
    }

    public void setNewprice(Double newprice) {
        this.newprice = newprice;
    }



    public String getPname() {
        return Pname;
    }

    public String getQuantity() {
        return Quantity;
    }

    public double getOldprice() {
        return oldprice;
    }

    public double getNewprice() {
        return newprice;
    }
    @Override
    public String toString() {
        return "{" +
                "'" + Pname + '\'' +
                ", oldprice=" + oldprice+"€" +
                ", newprice=" + newprice+"€" +
                '}';
    }

}

