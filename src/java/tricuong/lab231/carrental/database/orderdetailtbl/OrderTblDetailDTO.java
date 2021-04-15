/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.orderdetailtbl;

/**
 *
 * @author TriCuong
 */
public class OrderTblDetailDTO {

    private String idOrderDetail;
    private String idCar;
    private int quantity;
    private float price;
    private String idOrder;
    private String rentDate;
    private String returnDate;
    //Them thuoc tinh phu get Image
    private String img;
    private float unitPrice;

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public OrderTblDetailDTO(int quantity, float price, String rentDate, String returnDate, String img, float unitPrice) {
        this.quantity = quantity;
        this.price = price;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.img = img;
        this.unitPrice = unitPrice;
    }

    public OrderTblDetailDTO() {
    }

    public OrderTblDetailDTO(String idOrderDetail, String idCar, int quantity, float price, String idOrder, String rentDate, String returnDate) {
        this.idOrderDetail = idOrderDetail;
        this.idCar = idCar;
        this.quantity = quantity;
        this.price = price;
        this.idOrder = idOrder;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    /**
     * @return the idOrderDetail
     */
    public String getIdOrderDetail() {
        return idOrderDetail;
    }

    /**
     * @param idOrderDetail the idOrderDetail to set
     */
    public void setIdOrderDetail(String idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    /**
     * @return the idCar
     */
    public String getIdCar() {
        return idCar;
    }

    /**
     * @param idCar the idCar to set
     */
    public void setIdCar(String idCar) {
        this.idCar = idCar;
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

    /**
     * @return the idOrder
     */
    public String getIdOrder() {
        return idOrder;
    }

    /**
     * @param idOrder the idOrder to set
     */
    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * @return the rentDate
     */
    public String getRentDate() {
        return rentDate;
    }

    /**
     * @param rentDate the rentDate to set
     */
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}
