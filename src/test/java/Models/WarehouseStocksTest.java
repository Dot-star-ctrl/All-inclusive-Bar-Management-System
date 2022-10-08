package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.Item;
import system.barManagement.service.model.WarehouseStock;

public class WarehouseStocksTest {

    @Test
    public void setWarehouseStockIdTest() {
        //arrange
        WarehouseStock stock = new WarehouseStock(2,13);

        //act
        stock.setWarehouseStockId(1);

        //assert
        Assertions.assertEquals(1, stock.getWarehouseStockId());
    }

    @Test
    public void setItemIdTest() {
        //arrange
        WarehouseStock stock = new WarehouseStock(2,13);

        //act
        stock.setItemId(4);

        //assert
        Assertions.assertEquals(4, stock.getItemId());
    }

    @Test
    public void setItemQuantityTest() {
        //arrange
        WarehouseStock stock = new WarehouseStock(2,13);

        //act
        stock.setQuantity(10);

        //assert
        Assertions.assertEquals(10, stock.getQuantity());
    }
}
