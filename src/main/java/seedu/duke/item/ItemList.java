package seedu.duke.item;

import seedu.duke.Ui;
import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Objects;

public class ItemList {
    private final Ui ui = new Ui();
    private final ArrayList<Item> itemList;

    public ItemList() { //store files from data.txt
        //        this.itemList = fileItems;
        this.itemList = new ArrayList<Item>();
    }

    /**
     * Adds the newly created task to the task list.
     * @param item is the new task the user has created
     */
    public void addItem(Item item) {
        itemList.add(item);
        ui.addItemMessage(item, itemList);
    }

    public void getItemOfAnUser(String userId) {
        itemList.stream()
                .filter(t -> (t.getOwnerId().equals(userId)))
                .forEach(System.out::println);
    }

    public void getItemOfSpecificCategory(int categoryNumber) {
        itemList.stream()
                .filter(t -> (Category.setCategory(t.getCategory()) == categoryNumber))
                .forEach(System.out::println);
    }

    public void deleteItem(String itemId) {
        boolean found = false;
        for (Item item : itemList) {
            if (itemId.equals(item.getItemId())) {
                found = true;
                ui.deleteItemMessage(item, itemList);
                itemList.remove(item);
            }
        }
        if (!found) {
            System.out.println("There is no such item! Nothing to delete, aborting...\n");
        }
    }

    public void markAvailable(Item item) {
        int index = itemList.indexOf(item);
        getItem(index).setAsAvailable();
    }

    public void markUnavailable(Item item) {
        int index = itemList.indexOf(item);
        getItem(index).setAsNotAvailable();
    }

    public int getListSize() {
        return itemList.size();
    }

    public Item getItem(int index) {
        return itemList.get(index - 1);
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public void showList() {
        ui.showItemsList(itemList);
    }
}