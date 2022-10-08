package system.barManagement.service.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Employee;
import system.barManagement.service.resources.EmployeesResources;

import java.util.Collection;
import java.util.List;

public class HibernateEmployeesRepository {

    public List<Employee> getEmployees() throws BarManagementDBException {

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

    public Employee getEmployee(int employeeId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return employee;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read employee with id " + employeeId, e);
        }

    }

    public void create(Employee employee) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Integer employeeId = (Integer) session.save(employee);
            employee.setEmployeeId(employeeId); // set the auto-generated employee_number
            System.out.println(employeeId);
            session.getTransaction().commit();
            MyUtil.closeSession();
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot create employee " + employee, e);
        }
    }

    public boolean removeEmployee(int employeeId) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            session.delete(employee);
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read employee with id " + employeeId, e);
        }
    }

    public boolean updateEmployee(Employee employee) throws BarManagementDBException {
        try {
            Session session = MyUtil.getSession();
            session.beginTransaction();
            Employee oldEmployee = session.get(Employee.class, employee.getEmployeeId());
            oldEmployee.setUsername(employee.getUsername());
            oldEmployee.setPassword(employee.getPassword());
            oldEmployee.setAccountType(employee.getAccountType());
            session.getTransaction().commit();
            MyUtil.closeSession();
            return true;
        } catch (Exception e) {
            MyUtil.closeSessionFactory();
            throw new BarManagementDBException("Cannot read employee with id " + employee.getEmployeeId(), e);
        }
    }
}
