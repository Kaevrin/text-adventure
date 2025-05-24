
public class Interactable {
    public enum InteractableType {
        ENTERS_ROOM,
        UNLOCKS_FLAG
    }
    private final String name;
    private final String description;
    private final InteractableType type;
    private final String target;
    private final String conditionFlag;

    public Interactable(String name, String description, InteractableType type, String target, String conditionFlag) {
        this.name = name.toUpperCase();
        this.description = description;
        this.type = type;
        this.target = target;
        this.conditionFlag = conditionFlag;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void interact(Game game) {
        boolean conditionMet = conditionFlag == null || game.getFlag(conditionFlag);

        switch (type) {
            case ENTERS_ROOM:
                if (conditionMet) {
                    System.out.println("You go through the " + name + ".");
                    System.out.println("[DEBUG] ENTERS_ROOM TARGET: " + target);
                    game.enterRoom(target);
                } else {
                    System.out.println("It's sealed tight. Maybe something else unlocks it?");
                }
                break;

            case UNLOCKS_FLAG:
                if (conditionMet) {
                    System.out.println("You interact with the " + name + " and hear a click.");
                    game.setFlag(target, true);
                } else {
                    System.out.println("Nothing seems to happen.");
                }
                break;
        }
    }
}