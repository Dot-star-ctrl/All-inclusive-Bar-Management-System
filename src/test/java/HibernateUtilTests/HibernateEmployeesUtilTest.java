package HibernateUtilTests;

import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Bar;
import system.barManagement.service.model.Employee;
import system.barManagement.service.repository.MyUtil;

public class HibernateEmployeesUtilTest {

//    Models.EmployeesTest.js CRUD tests

    @BeforeAll
    static void ClearTableBefore(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table employees").executeUpdate();
        MyUtil.closeSession();
    }

    @Test
    public void testCreate() {
        System.out.println("Running testCreate...");

        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        Employee employee = new Employee("Stan", "password123", AccountType.HEAD_BARTENDER);
        //act
        Integer id = (Integer) session.save(employee);

        session.getTransaction().commit();
        //assert
        Assertions.assertTrue(id > 0);
    }

    @Test
    public void GetTest() {
        System.out.println("Running testGet...");
        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        Integer id = 1;
        //act
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        //assert
        Assertions.assertEquals("Stan", employee.getUsername());
    }

    @Test
    public void testUpdate() {
        System.out.println("Running testUpdate...");
        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        //act
        Integer id = 1;
        Employee employee = new Employee( "Simion","password", AccountType.BAR_MANAGER);
        Employee oldEmployee = session.get(Employee.class, id);
        oldEmployee.setUsername(employee.getUsername());
        oldEmployee.setPassword(employee.getPassword());
        oldEmployee.setAccountType(employee.getAccountType());

        session.getTransaction().commit();
        Employee updatedEmployee = session.find(Employee.class, id);

        //assert
        Assertions.assertEquals("Simion", updatedEmployee.getUsername());
    }

    @AfterEach
    public void closeSession() {
        MyUtil.closeSession();
    }

    @AfterAll
    static void ClearTable(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table employees").executeUpdate();
        MyUtil.closeSession();
    }
}
