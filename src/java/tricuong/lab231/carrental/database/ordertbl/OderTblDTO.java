/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.ordertbl;

/**
 *
 * @author TriCuong
 */
public class OderTblDTO {

    private String idOrder;
    private String userId;
    private float totalPrice;
    private String dateOrder;

    public OderTblDTO() {
    }

    public OderTblDTO(String idOrder, float totalPrice, String dateOrder) {
        this.idOrder = idOrder;
        this.totalPrice = totalPrice;
        this.dateOrder = dateOrder;
    }

    public OderTblDTO(String idOrder, String userId, float totalPrice, String dateOrder) {
        this.idOrder = idOrder;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.dateOrder = dateOrder;
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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the dateOrder
     */
    public String getDateOrder() {
        return dateOrder;
    }

    /**
     * @param dateOrder the dateOrder to set
     */
    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

}
