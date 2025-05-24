import java.util.*;

public class Game {
    private final Map<String, Room> rooms = new HashMap<>();
    private final Map<String, Boolean> globalFlags = new HashMap<>();
    private final Player player = new Player();
    private Room currentRoom;
    private final Scanner scanner = new Scanner(System.in);
    Item item = null;
    public void addRoom(Room room) {
        rooms.put(room.getName(), room);
    }

    public void setFlag(String flag, boolean value) {
        globalFlags.put(flag, value);
    }

    public boolean getFlag(String flag) {
        return globalFlags.getOrDefault(flag, false);
    }

    public Player getPlayer() {
        return player;
    }

    public void enterRoom(String roomName) {
        currentRoom = rooms.get(roomName);
        currentRoom.describe(this);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void start(String startingRoom) {
        
        enterRoom(startingRoom);
        Integer health = 100;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("QUIT")) break;
            processInput(input);
        }
    }

    public void processInput(String input) {
        String normalized = input.trim().toUpperCase();

        if (normalized.equals("INVENTORY")) {
            player.showInventory();
        } 
        else if (normalized.startsWith("USE ")) {
            String itemName = normalized.substring(4).trim();
            player.useItem(itemName, this);
        } 
        else if (normalized.startsWith("PICK UP ")) {
            String itemName = normalized.substring(8).trim();
            boolean pickedUp = currentRoom.pickupItem(itemName, player);
            if (!pickedUp) {
                System.out.println("No such item here.");
            }
        } 
        else if (normalized.startsWith("EXAMINE ")) {
            String examineName = normalized.substring(8).trim();
            Item item = player.getItemByName(examineName);
    
            if (item == null) {
                item = currentRoom.getItemByName(examineName);
            }

            if (item != null) {
                System.out.println(item.getDescription());
            } else {
                Interactable interactable = currentRoom.getInteractableByName(examineName);
                if (interactable != null) {
                    System.out.println(interactable.getDescription());
                } else {
                    System.out.println("You don't see a '" + examineName + "' here.");
                }
            }
        }
        else {
            Interactable interactable = currentRoom.getInteractableByName(normalized);
            if (interactable != null) {
                interactable.interact(this);
            } else {
                System.out.println("Nothing happens.");
            }
        }
    }
}