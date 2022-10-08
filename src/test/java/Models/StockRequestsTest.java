package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import system.barManagement.service.model.StockRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StockRequestsTest {

    @Test
    public void setStockRequestIdTest() {
        //arrange
        StockRequest stockRequest = new StockRequest(5);

        //act
        stockRequest.setStockRequestId(1);

        //assert
        Assertions.assertEquals(1, stockRequest.getStockRequestId());
    }

    @Test
    public void setBarIdTest() {
        //arrange
        StockRequest stockRequest = new StockRequest(5);

        //act
        stockRequest.setBarId(4);

        //assert
        Assertions.assertEquals(4, stockRequest.getBarId());
    }

    @Test
    public void setRequestDateTest() {
        //arrange
        StockRequest stockRequest = new StockRequest(5);

        //act
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String requestDate = simpleDateFormat.format(new Date());

        //assert
        Assertions.assertEquals(requestDate, stockRequest.getRequestDate());
    }

    @Test
    public void setReviewedByIdTest() {
        //arrange
        StockRequest stockRequest = new StockRequest(5);

        //act
        stockRequest.setReviewedById(12);

        //assert
        Assertions.assertEquals(12, stockRequest.getReviewedById());
    }

    @Test
    public void setIsApprovedTest() {
        //arrange
        StockRequest stockRequest = new StockRequest(5);

        //act
        stockRequest.setIsApproved(true);

        //assert
        Assertions.assertTrue(stockRequest.getIsApproved());
    }
}
