import java.util.*;

public class Room {
    private final String name;
    private final String baseDescription;
    private final Map<String, Interactable> interactables = new HashMap<>();
    private final Map<String, Item> items = new HashMap<>();

    public Room(String name, String baseDescription) {
        this.name = name;
        this.baseDescription = baseDescription;
    }

    public void describe(Game game) {
        System.out.println("[" + name + "]\n" + baseDescription);
        items.values().forEach(item -> System.out.println("You see a " + item.getName() + "."));
        interactables.keySet().forEach(key -> System.out.println("There is a " + key + " here."));
    }

    public Item getItemByName(String name) {
        return items.get(name.toUpperCase());
    }
    public Interactable getInteractableByName(String name) {
        return interactables.get(name.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public void addInteractable(String command, Interactable interactable) {
        interactables.put(command.toUpperCase(), interactable);
    }

    public void addItem(Item item) {
        items.put(item.getName().toUpperCase(), item);
    }
    
    public boolean pickupItem(String itemName, Player player) {
    String key = itemName.toUpperCase();
    if (items.containsKey(key)) {
        Item item = items.remove(key);
        player.addItem(item);
        return true;
    }
    return false;
}
}
