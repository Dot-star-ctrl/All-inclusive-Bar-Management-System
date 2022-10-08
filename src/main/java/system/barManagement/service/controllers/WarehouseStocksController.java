package system.barManagement.service.controllers;

import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.WarehouseStock;
import system.barManagement.service.repository.HibernateWarehouseStocksRepository;

import java.util.List;

public class WarehouseStocksController {

    HibernateWarehouseStocksRepository stocksRepository = new HibernateWarehouseStocksRepository();

    /**
     * Show/print all Warehouse stocks.
     */
    public List<WarehouseStock> showAllWarehouseStocks() {
        try {
            List<WarehouseStock> stocks = stocksRepository.getStocks();
            return stocks;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the stock with given stock id
     *
     * @param stockId of the stock to be shown.
     */
    public WarehouseStock showStock(int stockId) {
        try {
            WarehouseStock stock = stocksRepository.getWarehouseStock(stockId);
            return stock;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add/create new stock.
     *
     */
    public boolean addWarehouseStock(WarehouseStock stock) {
        try {
            if(stocksRepository.getWarehouseStock(stock.getWarehouseStockId()) != null){
                return false;
            }
            else{
                stocksRepository.create(stock);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a stock.
     *
     */
    public boolean updateWarehouseStock(WarehouseStock stock) {
        try {
            WarehouseStock old = stocksRepository.getWarehouseStock(stock.getWarehouseStockId());
            if (old == null) {
                return false;
            }
            else{
                stocksRepository.updateStock(stock);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a stock.
     *
     */
    public boolean deleteWarehouseStock(int stockId) {
        try {
            stocksRepository.removeStock(stockId);
            return true;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }
}
