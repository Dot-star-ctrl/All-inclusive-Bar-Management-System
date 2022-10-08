package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Employee;
import system.barManagement.service.model.Item;

public class ItemsTest {

    @Test
    public void setEmployeeIdTest() {
        //arrange
        Item item = new Item("orange juice",1.80);

        //act
        item.setItemId(2);

        //assert
        Assertions.assertEquals(2, item.getItemId());
    }

    @Test
    public void setItemNameTest() {
        //arrange
        Item item = new Item("orange juice",1.80);

        //act
        item.setItemName("apple juice");

        //assert
        Assertions.assertEquals("apple juice", item.getItemName());
    }

    @Test
    public void setItemPriceTest() {
        //arrange
        Item item = new Item("orange juice",1.80);

        //act
        item.setPrice(2.00);

        //assert
        Assertions.assertEquals(2.00, item.getPrice());
    }
}
