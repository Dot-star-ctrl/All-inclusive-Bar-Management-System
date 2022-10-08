package system.barManagement.service.model;

import java.util.Objects;

public class WarehouseStock {
    private int id;
    private int itemId;
    private int itemQuantity;

    public WarehouseStock(int itemId, int itemQuantity){
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
    }

    public WarehouseStock() {
    }

    public int getWarehouseStockId(){return id;}

    public void setWarehouseStockId(int id){this.id = id;}

    public int getItemId(){return itemId;}

    public void setItemId(int  itemId){this. itemId =  itemId;}

    public int getQuantity(){return itemQuantity;}

    public void setQuantity(int  itemQuantity){this. itemQuantity =  itemQuantity;}
}
