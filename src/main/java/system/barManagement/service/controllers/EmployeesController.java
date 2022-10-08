package system.barManagement.service.controllers;

import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Employee;
import system.barManagement.service.repository.HibernateEmployeesRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeesController {

    HibernateEmployeesRepository employeesRepository = new HibernateEmployeesRepository();

    /**
     * Show/print the employee with given employee id
     *
     * @param employeeId of the employee to be shown.
     */
    public Employee showEmployee(int employeeId) {
        try {
            Employee employee = employeesRepository.getEmployee(employeeId);
            return employee;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print all employees.
     */
    public List<Employee> showAllEmployees() {
        try {
            List<Employee> employees = employeesRepository.getEmployees();
            return employees;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add/create a new employee.
     *
     */
    public boolean addEmployee(Employee employee) {
        try {
            if(employeesRepository.getEmployee(employee.getEmployeeId()) != null){
                return false;
            }
            else{
                employeesRepository.create(employee);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an employee.
     *
     */
    public boolean deleteEmployee(int employeeId) {
        try {
            employeesRepository.removeEmployee(employeeId);
            return true;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an employee.
     *
     */
    public boolean updateEmployee(Employee employee) {
        try {
            Employee old = employeesRepository.getEmployee(employee.getEmployeeId());
            if (old == null) {
                return false;
            }
            else{
                employeesRepository.updateEmployee(employee);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> getEmployeesByAccountType(AccountType accountType){
        try {
            List<Employee> employeesByAccount = new ArrayList<Employee>();
            for (Employee employee : employeesRepository.getEmployees()) {
                if (employee.getAccountType() == accountType) {
                    employeesByAccount.add(employee);
                }
            }
            return employeesByAccount;
        }catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
