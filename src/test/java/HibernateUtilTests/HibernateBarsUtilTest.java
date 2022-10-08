package HibernateUtilTests;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import system.barManagement.service.model.Bar;
import system.barManagement.service.repository.MyUtil;

public class HibernateBarsUtilTest {

    //    Models.BarsTest.js CRUD tests

    @BeforeAll
    static void ClearTableBefore(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table bars").executeUpdate();
        MyUtil.closeSession();
    }

    @Test
    public void testCreate() {
        System.out.println("Running testCreate...");

        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        Bar bar = new Bar("Lobby Bar");
        bar.setAssignedBartender(1);

        //act
        Integer id = (Integer) session.save(bar);

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
        Bar bar = session.get(Bar.class, id);
        //assert
        Assertions.assertEquals("Lobby Bar", bar.getBarName());

    }

    @Test
    public void testUpdate() {
        System.out.println("Running testUpdate...");
        //arrange
        Session session = MyUtil.getSession();
        session.beginTransaction();

        //act
        Integer id = 1;
        Bar bar = new Bar( "Pool Bar");
        bar.setAssignedBartender(2);

        Bar oldBar = session.get(Bar.class, id);
        oldBar.setBarName(bar.getBarName());
        oldBar.setAssignedBartender(bar.getAssignedBartender());

        session.getTransaction().commit();
        Bar updatedBar = session.find(Bar.class, id);

        //assert
        Assertions.assertEquals("Pool Bar", updatedBar.getBarName());
    }

    @AfterEach
    public void closeSession() {
        MyUtil.closeSession();
    }

    @AfterAll
    static void ClearTable(){
        Session session = MyUtil.getSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table bars").executeUpdate();
        MyUtil.closeSession();
    }
}
