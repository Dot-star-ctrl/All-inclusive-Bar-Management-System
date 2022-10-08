package system.barManagement.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bar {
    private int id;
    private String barName;
    private int assignedBartender;

    public Bar(String barName){
        this.barName = barName;
    }

    public Bar() {
    }

    public int getBarId(){return id;}

    public void setBarId(int id){this.id = id;}

    public String getBarName(){return barName;}

    public void setBarName(String barName){this.barName = barName;}

    public int getAssignedBartender(){return assignedBartender;}

    public void setAssignedBartender(int assignedBartender){this.assignedBartender = assignedBartender;}
}
