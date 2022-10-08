package system.barManagement.service.controllers;

import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Bar;
import system.barManagement.service.repository.HibernateBarsRepository;

import java.util.List;

public class BarsController {

    HibernateBarsRepository barsRepository = new HibernateBarsRepository();

    /**
     * Show/print all bars.
     *
     */
    public List<Bar> showAllBars() {
        try {
            List<Bar> bars = barsRepository.getBars();
            System.out.println("All bars");
            for (Bar bar : bars) {
                System.out.println("\t" + bar);
            }
            return bars;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the bar with given bar id
     *
     */
    public Bar showBar(int barId) {
        try {
            Bar bar = barsRepository.getBar(barId);
            return bar;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add/create a new bar.
     *
     */
    public boolean addBar(Bar bar) {
        try {
            if(barsRepository.getBar(bar.getBarId()) != null){
                return false;
            }
            else{
                barsRepository.create(bar);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a bar.
     *
     */
    public boolean deleteBar(int barId) {
        try {
            barsRepository.removeBar(barId);
            return true;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a bar.
     *
     */
    public boolean updateBar(Bar bar) {
        try {
            Bar old = barsRepository.getBar(bar.getBarId());
            if (old == null) {
                return false;
            }
            else{
                barsRepository.updateBar(bar);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }
}
