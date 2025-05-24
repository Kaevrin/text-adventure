import java.util.HashMap;
import java.util.Map;

public class Worldbuilder {
     private static Map<String, Room> rooms;

    public static Map<String, Room> buildWorld() {
        rooms = new HashMap<>();
        //creating example
        createRoom("EXAMPLE", "The description of the area.", room -> {

        });
        //Creating Vault
        createRoom("VAULT", "A sealed, hexagonal chamber lined with deactivated systems and empty pod shells. A prominent hatch is rusted shut on the far wall, while a flickering console hums softly beside it.", room -> {
            room.addItem(new Item("Scrap Pipe", "pipe description", "hatchOpen","You use the pipe to force open the rusted hatch. The hatch groans open, revealing a path.", "VAULT"));
            room.addItem(new Item("Steam Cell", "steam cell description", "reactorFueled","You install the steam cell. The Reactor hums to life.", "REACTOR"));
            room.addInteractable("Rusted Hatch", new Interactable("Rusted Hatch", "A rusted hatch in the wall.", Interactable.InteractableType.ENTERS_ROOM, "FOUNDRY", "hatchOpen"));
        });

        //Creating Foundry
        createRoom("FOUNDRY", "A cavernous chamber filled with skeletal gantries and the stale stench of oil. Old machinery shudders as pressure still leaks from forgotten lines. Heat lingers in the air, and faint light filters in from broken vents above.", room -> {
            room.addInteractable("rusted hatch", new Interactable("Rusted Hatch", "A rusted hatch in the wall.", Interactable.InteractableType.ENTERS_ROOM, "VAULT", "hatchOpen"));
            room.addInteractable("crawlway access", new Interactable("Crawlway Access", "A tight passage winding through the facility’s understructure. Cramped, hot, and unpleasant — but it looks like it connects to several key areas.", Interactable.InteractableType.ENTERS_ROOM, "TUNNELS", null));
            room.addInteractable("iron shutter", new Interactable("Iron Shutter", "A narrow metal doorway set low into the foundry wall. The Iron Shutter is streaked with soot and warped from years of heat, its sliding track partially buried in grime. A faded label above reads, “Maintenance Access.” The handle is stiff but reachable.", Interactable.InteractableType.ENTERS_ROOM, "ALLEY", null));
            room.addInteractable("brass door", new Interactable("Brass Door", "a brass door", Interactable.InteractableType.ENTERS_ROOM, "ELEVATOR", null));
        });

        //Creating Tunnels
        createRoom("TUNNELS", "The passage narrows into a twisting maze of copper piping and reinforced struts, dimly lit by flickering amber bulbs. Condensation drips from overhead valves, and the air hums with the deep pulse of distant machinery. The walls close in tightly, forcing you to stoop or crawl in some stretches.", room -> {
            room.addInteractable("crawlway access", new Interactable("Crawlway Access", "A tight crawlspace hatch worn smooth from years of use. Beyond it, the glow of the Foundry flickers against the grime.", Interactable.InteractableType.ENTERS_ROOM, "FOUNDRY", null));
            room.addInteractable("access ladder", new Interactable("Access Ladder", "A narrow iron ladder bolts to the wall, leading up into darkness. The faint vibration of machinery thrums above.", Interactable.InteractableType.ENTERS_ROOM, "ENGINE", null));
            room.addInteractable("sealed bulkhead", new Interactable("Sealed Bulkhead", "A thick steel bulkhead stained with faded hazard markings. The metal hums faintly with latent energy.", Interactable.InteractableType.ENTERS_ROOM, "REACTOR", null));
        });
        //Creating Reactor
        createRoom("REACTOR", "Its a reactor room", room -> {
            room.addInteractable("sealed bulkhead", new Interactable("Sealed Bulkhead", "Sealed Bulkhead Reactor desc.", Interactable.InteractableType.ENTERS_ROOM, "TUNNELS", null));
            room.addInteractable("steam reactor", new Interactable("Steam Reactor", "Steam Reactor desc.", Interactable.InteractableType.UNLOCKS_FLAG, "poweredUp", "reactorFueled"));

        });
        //Creating Alley
        createRoom("ALLEY", "A soot-choked alleyway wedged between towering industrial buildings. Blackened bricks sweat with moisture, and riveted steam pipes line the walls, intermittently releasing hisses of pressure. A collapsed automaton slumps beside a corroded maintenance post, its interior panels pried open and hollow. Faded signage creaks overhead, half-swallowed by ivy. One end of the alley terminates at a tall iron gate, its lock rusted but intact, barring the way forward.", room -> {
            room.addInteractable("iron shutter", new Interactable("Iron Shutter", "Half-concealed behind rusted crates and piping, the Iron Shutter marks a back entrance into the foundry. Its steel surface is grimy and dented, with a weathered handle and just enough space beneath to suggest recent use.", Interactable.InteractableType.ENTERS_ROOM, "FOUNDRY", null));
        });

        //Creating Elevator
        createRoom("ELEVATOR", "The description of the area.", room -> {
            room.addInteractable("brass door", new Interactable("Brass Door", "a brass door", Interactable.InteractableType.ENTERS_ROOM, "FOUNDRY", null));
            room.addInteractable("steam shaft", new Interactable("Steam Shaft", "a steam powered elevator", Interactable.InteractableType.ENTERS_ROOM, "AIRPAD", "poweredUp"));
        });
        
        //Creating Airpad
        createRoom("AIRPAD", "The description of the area.", room -> {
            room.addInteractable("steam shaft", new Interactable("Steam Shaft", "a steam powered elevator", Interactable.InteractableType.ENTERS_ROOM, "ELEVATOR", null));
            room.addInteractable("airship", new Interactable("Airship", "an airship", Interactable.InteractableType.ENTERS_ROOM, "END", null));
        });
        
        //Creating End
        createRoom("END", "You escape into the skies.", room -> {
            
        });

        //Returning all created Rooms
        return rooms;
    }


    private static void createRoom(String name, String description, java.util.function.Consumer<Room> setup) {
        Room room = new Room(name, description);
        setup.accept(room);
        rooms.put(name, room);
    }
}
