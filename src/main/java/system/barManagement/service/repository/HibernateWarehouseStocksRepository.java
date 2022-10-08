package system.barManagement.service.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Employee;
import system.barManagement.service.model.Item;
import system.barManagement.service.model.WarehouseStock;
import system.barManagement.service.resources.WarehouseResources;

import java.util.List;

public class HibernateWarehouseStocksRepository {
    public List<WarehouseStock> getStocks() throws BarManagementDBException {

        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            List<WarehouseStock> result = session.createQuery("from WarehouseStock ", WarehouseStock.class).list();

            for (WarehouseStock stock : result) {
                Hibernate.initialize(stock.getItemId());
            }
            session.getTransaction().commit();
            MyUtil.closeSession();
            return result;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read stock from the database", e);
        }
    }

    public WarehouseStock getWarehouseStock(int stockId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            WarehouseStock stock = session.get(WarehouseStock.class, stockId);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return stock;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read stock with id " + stockId, e);
        }

    }

    public void create(WarehouseStock stock) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Integer stockId = (Integer) session.save(stock);
            stock.setWarehouseStockId(stockId);// set the auto-generated stock_number
            System.out.println(stockId);
            session.getTransaction().commit();
            MyUtil.closeSession();
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot create stock " + stock, e);
        }
    }

    public boolean removeStock(int stockId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            WarehouseStock stock = session.get(WarehouseStock.class, stockId);
            session.delete(stock);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read stock with id " + stockId, e);
        }
    }

    public boolean updateStock(WarehouseStock stock) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            WarehouseStock oldStock = session.get(WarehouseStock.class, stock.getWarehouseStockId());
            oldStock.setItemId(stock.getItemId());
            oldStock.setQuantity(stock.getQuantity());
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read stock with id " + stock.getWarehouseStockId(), e);
        }
    }
}
