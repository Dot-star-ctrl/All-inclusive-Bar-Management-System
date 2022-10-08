package system.barManagement.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;
import java.text.SimpleDateFormat;


public class StockRequest {
    private int id;
    private int barId;
    String pattern = "MM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private final String requestDate = simpleDateFormat.format(new Date());
    private int reviewedById;
    private boolean isApproved = false;

    public StockRequest(int barId){
        this.barId = barId;
    }

    public StockRequest() {
    }

    public int getStockRequestId(){return id;}

    public void setStockRequestId(int id){this.id = id;}

    public int getBarId(){return barId;}

    public void setBarId(int barId){this.barId = barId;}

    public String getRequestDate(){return requestDate;}

    public int getReviewedById(){return reviewedById;}

    public void setReviewedById(int reviewedById){this.reviewedById = reviewedById;}

    public boolean getIsApproved(){return isApproved;}

    public void setIsApproved(boolean isApproved){this.isApproved = isApproved;}
}
