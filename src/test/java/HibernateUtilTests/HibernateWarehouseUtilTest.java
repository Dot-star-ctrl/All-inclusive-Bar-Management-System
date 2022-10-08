package HibernateUtilTests;

import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.WarehouseStock;
import system.barManagement.service.repository.MyUtil;

public class HibernateWarehouseUtilTest {

    //    Models.ItemsTest.js CRUD tests

    @BeforeAll
    static void ClearTableBefore(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table warehouse").executeUpdate();
        MyUtil.closeSession();
    }

    @Test
    public void testCreate() {
        System.out.println("Running testCreate...");

        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        WarehouseStock stock = new WarehouseStock(1, 10);
        //act
        Integer id = (Integer) session.save(stock);

        session.getTransaction().commit();
        //assert
        Assertions.assertTrue(id > 0);
    }

    @Test
    public void GetTest() {
        System.out.println("Running testGet...");
        //arrange
        Session session = MyUtil.getSession();

        Integer id = 1;
        //act
        WarehouseStock stock = session.get(WarehouseStock.class, id);
        //assert
        Assertions.assertEquals(1, stock.getItemId());

    }

    @Test public void testUpdate() {
        System.out.println("Running testUpdate...");
        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        //act
        Integer id = 1;
        WarehouseStock stock = new WarehouseStock(4,8);
        WarehouseStock oldStock = session.get(WarehouseStock.class, id);
        oldStock.setItemId(stock.getItemId());
        oldStock.setQuantity(stock.getQuantity());

        session.getTransaction().commit();
        WarehouseStock updatedStock = session.find(WarehouseStock.class, id);

        //assert
        Assertions.assertEquals(4, updatedStock.getItemId());
    }

    @AfterEach
    public void closeSession() {
        MyUtil.closeSession();
    }

    @AfterAll
    static void ClearTable(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table warehouse").executeUpdate();
        MyUtil.closeSession();
    }
}
