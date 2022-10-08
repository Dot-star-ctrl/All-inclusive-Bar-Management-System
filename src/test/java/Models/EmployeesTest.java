package Models;

import io.jsonwebtoken.lang.Assert;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Employee;
import system.barManagement.service.repository.MyUtil;

public class EmployeesTest {

    @Test
    public void setEmployeeIdTest() {
        //arrange
        Employee employee = new Employee("Ian", "pass123", AccountType.BAR_MANAGER);

        //act
        employee.setEmployeeId(1);

        //assert
        Assertions.assertEquals(1, employee.getEmployeeId());
    }

    @Test
    public void setUsernameTest() {
        //arrange
        Employee employee = new Employee("Ian", "pass123", AccountType.BAR_MANAGER);

        //act
        employee.setUsername("Stan");

        //assert
        Assertions.assertEquals("Stan", employee.getUsername());
    }

    @Test
    public void setPasswordTest() {
        //arrange
        Employee employee = new Employee("Ian", "pass123", AccountType.BAR_MANAGER);

        //act
        employee.setPassword("password");

        //assert
        Assertions.assertEquals("password", employee.getPassword());
    }

    @Test
    public void setAccountTypeTest() {
        //arrange
        Employee employee = new Employee("Ian", "pass123", AccountType.BAR_MANAGER);

        //act
        employee.setAccountType(AccountType.BAR_SUPPLIER);

        //assert
        Assertions.assertEquals(AccountType.BAR_SUPPLIER, employee.getAccountType());
    }
}
