/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.cartbl;

/**
 *
 * @author TriCuong
 */
public class CarTblDTO {
    private String carID;
    private String namee;
    private String image;
    private String type;
    private String color;
    private int quantity;
    private float price;

    public CarTblDTO() {
    }

    public CarTblDTO(String carID, String namee, String image, String type, String color, int quantity, float price) {
        this.carID = carID;
        this.namee = namee;
        this.image = image;
        this.type = type;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
    }

    public CarTblDTO(String namee, String image, String type, String color, int quantity, float price) {
        this.namee = namee;
        this.image = image;
        this.type = type;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
    }

    public CarTblDTO(String carID, String namee, String image, String type, String color, float price) {
        this.carID = carID;
        this.namee = namee;
        this.image = image;
        this.type = type;
        this.color = color;
        this.price = price;
    }

    /**
     * @return the carID
     */
    public String getCarID() {
        return carID;
    }

    /**
     * @param carID the carID to set
     */
    public void setCarID(String carID) {
        this.carID = carID;
    }

    /**
     * @return the namee
     */
    public String getNamee() {
        return namee;
    }

    /**
     * @param namee the namee to set
     */
    public void setNamee(String namee) {
        this.namee = namee;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
}
