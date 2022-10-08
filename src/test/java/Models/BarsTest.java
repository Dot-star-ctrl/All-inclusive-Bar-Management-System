package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Bar;
import system.barManagement.service.model.Employee;

public class BarsTest {

    @Test
    public void setBarIdTest() {
        //arrange
        Bar bar = new Bar("Lobby bar");


        //act
        bar.setBarId(1);

        //assert
        Assertions.assertEquals(1, bar.getBarId());
    }

    @Test
    public void setBarNameTest() {
        //arrange
        Bar bar = new Bar("Lobby bar");

        //act
        bar.setBarName("Snack Bar");

        //assert
        Assertions.assertEquals("Snack Bar", bar.getBarName());
    }

    @Test
    public void setAssignedBartenderTest() {
        //arrange
        Bar bar = new Bar("Lobby bar");
        int assignedBartenderId = 1;

        //act
        bar.setAssignedBartender(8);

        //assert
        Assertions.assertEquals(8, bar.getAssignedBartender());
    }
}
