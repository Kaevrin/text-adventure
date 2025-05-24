import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Map<String, Room> gameWorld = Worldbuilder.buildWorld();
        for (Room room : gameWorld.values()) {
            game.addRoom(room);
        }
        //starting the game with the desired beginning room
        game.start("VAULT");
    }
}