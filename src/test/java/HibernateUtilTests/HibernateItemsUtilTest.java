package HibernateUtilTests;

import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.Item;
import system.barManagement.service.repository.MyUtil;

public class HibernateItemsUtilTest {

    //    Models.ItemsTest.js CRUD tests

    @BeforeAll
    static void ClearTableBefore(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table items").executeUpdate();
        MyUtil.closeSession();
    }

    @Test
    public void testCreate() {
        System.out.println("Running testCreate...");

        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        Item item = new Item("Apple Juice", 1.20);
        //act
        Integer id = (Integer) session.save(item);

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
        Item item = session.get(Item.class, id);
        //assert
        Assertions.assertEquals("Apple Juice", item.getItemName());

    }

    @Test public void testUpdate() {
        System.out.println("Running testUpdate...");
        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        //act
        Integer id = 1;
        Item item = new Item( "Red Wine",8.20);
        Item oldItem = session.get(Item.class, id);
        oldItem.setItemName(item.getItemName());
        oldItem.setPrice(item.getPrice());

        session.getTransaction().commit();
        Item updatedItem = session.find(Item.class, id);

        //assert
        Assertions.assertEquals("Red Wine", updatedItem.getItemName());
    }

    @AfterEach
    public void closeSession() {
        MyUtil.closeSession();
    }

    @AfterAll
    static void ClearTable(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table items").executeUpdate();
        MyUtil.closeSession();
    }
}
