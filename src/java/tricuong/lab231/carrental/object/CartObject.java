/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.object;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import tricuong.lab231.carrental.database.cartbl.CarTblDTO;

/**
 *
 * @author TriCuong
 */
public class CartObject {

    private Map<CarTblDTO, Integer> items;

    public int getSize() {
        return items.size();
    }

    public Map<CarTblDTO, Integer> getItems() {
        return items;
    }

    public boolean checkExisted(String id) {
        Set<CarTblDTO> set = items.keySet();
        for (CarTblDTO dto : set) {
            if (dto.getCarID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void upQuantity(String id) {
        Set<CarTblDTO> set = items.keySet();
        for (CarTblDTO dto : set) {
            if (dto.getCarID().equals(id)) {
                this.getItems().put(dto, items.get(dto) + 1);
                return;
            }
        }
    }

    public void updateQuantity(String id, int value) {
        Set<CarTblDTO> set = items.keySet();
        for (CarTblDTO dto : set) {
            if (dto.getCarID().equals(id)) {
                this.getItems().put(dto, value);
                return;
            }
        }
    }

    public void addItemToCart(CarTblDTO dto) {
        if (this.getItems() == null) {
            this.setItems(new HashMap<>());
        }//end if items is not existed
        int quantity = 1;
        if (checkExisted(dto.getCarID())) {
            upQuantity(dto.getCarID());
            return;
        }
        this.getItems().put(dto, quantity);
    }

    public float getPrice(long numberDays) {
        float total = 0;
        Set<CarTblDTO> set = items.keySet();
        for (CarTblDTO dto : set) {
            total = total + dto.getPrice() * items.get(dto);
        }
        return total * (float) numberDays;
    }

    public void removeItemFromCart(String proId) {
        if (this.getItems() == null) {
            return;
        }
        Set<CarTblDTO> set = items.keySet();
        for (CarTblDTO dto : set) {
            if (dto.getCarID().equals(proId)) {
                this.getItems().remove(dto);
                return;
            }
        }
    }
//    public void removeItemFromCart(TblProductsDTO dto){
//        if(this.getItems() ==null){
//            return;
//        }
//        if(this.getItems().containsKey(dto)){
//            this.getItems().remove(dto);
//            if(this.getItems().isEmpty()){
//                this.setItems(null);
//            }
//        }
//    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<CarTblDTO, Integer> items) {
        this.items = items;
    }
}
