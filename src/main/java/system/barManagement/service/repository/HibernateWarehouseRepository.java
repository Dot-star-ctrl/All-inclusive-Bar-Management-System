package system.barManagement.service.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Employee;

import java.util.List;

public class HibernateWarehouseRepository {

    public List<Employee> getWarehouseItems() throws BarManagementDBException {

        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            List<Employee> result = session.createQuery("from Employee ", Employee.class).list();

            for (Employee employee : result) {
                Hibernate.initialize(employee.getUsername());
            }
            session.getTransaction().commit();
            MyUtil.closeSession();
            return result;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read employees from the database", e);
        }
    }
}
