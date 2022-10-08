package system.barManagement.service.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Bar;
import org.hibernate.cfg.Configuration;
import system.barManagement.service.model.Employee;

import java.util.List;

public class HibernateBarsRepository {

    public List<Bar> getBars() throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            List<Bar> result = session.createQuery("from Bar ", Bar.class).list();
            for (Bar bar : result) {
                Hibernate.initialize(bar.getBarName());
            }
            session.getTransaction().commit();
            MyUtil.closeSession();
            return result;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read bars from the database", e);
        }
    }

    public Bar getBar(int barId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Bar bar = session.get(Bar.class, barId);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return bar;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read bar with id " + barId, e);
        }

    }

    public void create(Bar bar) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            // insert bar to the database and get the auto-generated bar_id
            Integer barId = (Integer) session.save(bar);
            bar.setBarId(barId); // set the auto-generated bar_id
            session.getTransaction().commit();
            System.out.println(barId);
            MyUtil.closeSession();
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot create bar " + bar, e);
        }
    }

    public boolean removeBar(int barId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Bar bar = session.get(Bar.class, barId);
            session.delete(bar);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read bar with id " + barId, e);
        }
    }

    public boolean updateBar(Bar bar) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Bar oldBar = session.get(Bar.class, bar.getBarId());
            oldBar.setBarName(bar.getBarName());
            oldBar.setAssignedBartender(bar.getAssignedBartender());
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read bar with id " + bar.getBarId(), e);

        }
    }
}
