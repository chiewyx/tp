package seedu.duke.item;

import seedu.duke.id.IdGenerator;
import seedu.duke.transaction.TransactionList;

public class Item {
    private final String itemId;
    private final String name;
    private final String ownerId;
    private final double pricePerDay;
    private final Category.Categories category;

    public Item(String name, int categoryNumber, double price, String ownerId) {
        this.itemId = IdGenerator.generateId();
        this.name = name;
        this.pricePerDay = price;
        this.ownerId = ownerId;
        this.category = Category.mapCategory(categoryNumber);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public Category.Categories getCategory() {
        return category;
    }

    public boolean isAvailable(TransactionList transactionList) {
        return !transactionList.hasThisItemBeingBorrowed(name);
    }

    public String getStatus(TransactionList transactionList) {
        return (this.isAvailable(transactionList) ? "YES" : "NO");
    }

    public String toString(TransactionList transactionList) {
        String itemId = "itemId: " + this.itemId + " ";
        String itemIcon = "Status: [" + (isAvailable(transactionList) ? "Available" : "On loan") + "] ";
        String itemName = "Item: " + name + " ";
        String itemCategory = "Category: " + category.toString() + " ";
        String itemOwner = "Owner: " + getOwnerId() + " ";
        String itemPrice = "PricePerDay: $" + pricePerDay;
        return itemIcon + itemId + itemName + itemCategory + itemOwner + itemPrice;
    }
}