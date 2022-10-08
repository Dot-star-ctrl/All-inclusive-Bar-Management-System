package system.barManagement.service.controllers;

import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Item;
import system.barManagement.service.repository.HibernateItemsRepository;

import java.util.List;

public class ItemsController {

    HibernateItemsRepository itemsRepository = new HibernateItemsRepository();

    /**
     * Show/print all items.
     *
     */
    public List<Item> showAllItems() {
        try {
            List<Item> items = itemsRepository.getItems();
            System.out.println("All items");
            for (Item item : items) {
                System.out.println("\t" + item);
            }
            return items;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the item with given item id
     *
     * @param itemId of the item to be shown.
     */
    public Item showItem(int itemId) {
        try {
            Item item = itemsRepository.getItem(itemId);
            return item;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add/create a new item.
     *
     */
    public boolean addItem(Item item) {
        try {
            if(itemsRepository.getItem(item.getItemId()) != null){
                return false;
            }
            else{
                itemsRepository.create(item);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an item.
     *
     */
    public boolean updateItem(Item item) {
        try {
            Item old = itemsRepository.getItem(item.getItemId());
            if (old == null) {
                System.out.println("false");
                return false;
            }
            else{
                System.out.println("true");
                itemsRepository.updateItem(item);
                return true;
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an item.
     *
     */
    public boolean deleteItem(int itemId) {
        try {
            itemsRepository.removeItem(itemId);
            return true;
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
    }
}
