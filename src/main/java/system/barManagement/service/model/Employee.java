package system.barManagement.service.model;

import java.util.Objects;

public class Employee {

    private int id;
    private String username;
    private String password;
    private AccountType accountType;

    public Employee(String username, String password, AccountType accountType){
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public Employee() {
    }

    public int getEmployeeId(){return id;}

    public void setEmployeeId(int id){this.id = id;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public AccountType getAccountType(){return accountType;}

    public void setAccountType(AccountType accountType){this.accountType = accountType;}
}
