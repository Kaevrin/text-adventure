public class Item {
    private final String name;
    private final String flagToSet;
    private final String useMessage;
    private final String room;
    private final String itemDescription;
    private final String hazard;

    public Item(String name, String itemDescription, String flagToSet, String useMessage, String room, String hazard) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.flagToSet = flagToSet;
        this.useMessage = useMessage;
        this.room = room;
        this.hazard = hazard;
    }
    public Item(String name, String itemDescription, String flagToSet, String useMessage, String room) {
        this(name, itemDescription, flagToSet, useMessage, room, null);
    }

    public String getName() {
        return name.toUpperCase();
    }
    public String getDescription() {
        return itemDescription;
    }
    public void use(Game game) {
        if (flagToSet != null && game.getCurrentRoom().getName().equals(room)) {
            game.setFlag(flagToSet, true);
            System.out.println(useMessage);
        }
        else {
            System.out.println("You can't seem to find a use for this here.");
        }
    }
}