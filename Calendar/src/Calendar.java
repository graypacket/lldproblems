import event.Event;
import event.EventManager;
import user.User;
import user.UserManager;

public class Calendar {
    private static final UserManager userManager = new UserManager();
    private static final EventManager eventManager = new EventManager();
    public static void main(String[] args) {
        User graypacket = userManager.createUser("Anshuman", "Sisodia", "graypacket");
        User rajvar = userManager.createUser("Raj", "Sisodia", "rajvar");
        User rishika = userManager.createUser("Rishika", "Sisodia", "rishika");

        Event dinner = eventManager.createEvent(graypacket, "Dinner");
        eventManager.addGuest(dinner.getId(), rajvar);
        eventManager.addGuest(dinner.getId(), rishika);

        for(Event event : eventManager.getEventsForUser(graypacket))
            System.out.println(event);

        for(Event event: eventManager.getEventsForUser(rajvar)) {
            eventManager.acceptEvent(event.getId(), rajvar); 
        }
        for(Event event: eventManager.getEventsForUser(rishika)) {
            eventManager.acceptEvent(event.getId(), rishika); 
        }

        for(Event event : eventManager.getEventsForUser(graypacket))
            System.out.println(event);
    }
}
