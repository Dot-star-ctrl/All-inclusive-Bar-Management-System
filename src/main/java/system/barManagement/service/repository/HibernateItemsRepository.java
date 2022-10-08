package system.barManagement.service.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Item;

import java.util.List;

public class HibernateItemsRepository {

    public List<Item> getItems() throws BarManagementDBException {

        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            List<Item> result = session.createQuery("from Item ", Item.class).list();

            for (Item item : result) {
                Hibernate.initialize(item.getItemName());
            }
            session.getTransaction().commit();
            MyUtil.closeSession();
            return result;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read items from the database", e);
        }
    }

    public Item getItem(int itemId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Item item = session.get(Item.class, itemId);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return item;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read item with id " + itemId, e);
        }
    }

    public void create(Item item) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Integer itemId = (Integer) session.save(item);
            item.setItemId(itemId); // set the auto-generated item_number
            System.out.println(itemId);
            session.getTransaction().commit();
            MyUtil.closeSession();
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot create item " + item, e);
        }
    }

    public boolean removeItem(int itemId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Item item = session.get(Item.class, itemId);
            session.delete(item);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read item with id " + itemId, e);
        }
    }

    public boolean updateItem(Item item) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Item oldItem = session.get(Item.class, item.getItemId());
            oldItem.setItemName(item.getItemName());
            oldItem.setPrice(item.getPrice());
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read item with id " + item.getItemId(), e);
        }
    }
}
