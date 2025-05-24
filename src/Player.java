import java.util.*;
public class Player {
    private final List<Item> inventory = new ArrayList<>();

    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("You picked up: " + item.getName());
    }

    public void useItem(String name, Game game) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.use(game);
                return;
            }
        }
        System.out.println("You don't have that.");
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("You don't have anything.");
        }
        else {
            System.out.println("You are carrying:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
    }

    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}