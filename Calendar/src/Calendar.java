import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import event.Event;
import event.EventManager;
import user.User;
import user.UserManager;

public class Calendar {
    private static final UserManager userManager = new UserManager();
    private static final EventManager eventManager = new EventManager();

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy kk:mm";
    private static final String DAY_FORMAT = "dd-MM-yyyy";

    public static void main(String[] args) throws ParseException {
        User graypacket = userManager.createUser("Anshuman", "Sisodia", "graypacket");
        User rajvar = userManager.createUser("Raj", "Sisodia", "rajvar");
        User rishika = userManager.createUser("Rishika", "Sisodia", "rishika");

        Event dinner = eventManager.createEvent(graypacket, "Dinner");
        eventManager.setEventStart(dinner.getId(), new SimpleDateFormat(DATE_TIME_FORMAT).parse("25-10-2021 10:00"));
        eventManager.setEventEnd(dinner.getId(), new SimpleDateFormat(DATE_TIME_FORMAT).parse("25-10-2021 11:00"));
        eventManager.addGuest(dinner.getId(), rajvar);
        eventManager.addGuest(dinner.getId(), rishika);

        Event bikeRide = eventManager.createEvent(rajvar, "bike ride");
        eventManager.setEventStart(bikeRide.getId(), new SimpleDateFormat(DATE_TIME_FORMAT).parse("25-10-2021 15:00"));
        eventManager.setEventEnd(bikeRide.getId(), new SimpleDateFormat(DATE_TIME_FORMAT).parse("25-10-2021 16:00"));

        Event movie = eventManager.createEvent(rishika, "movie");
        eventManager.setEventStart(movie.getId(), new SimpleDateFormat(DATE_TIME_FORMAT).parse("25-10-2021 15:30"));
        eventManager.setEventEnd(movie.getId(), new SimpleDateFormat(DATE_TIME_FORMAT).parse("25-10-2021 18:30"));
        //eventManager.addGuest(movie.getId(), graypacket);

        //eventManager.addGuest(bikeRide.getId(), graypacket);
        
        for(Event event: eventManager.getEventsForUser(rajvar)) {
            eventManager.acceptEvent(event.getId(), rajvar); 
        }
        for(Event event: eventManager.getEventsForUser(rishika)) {
            eventManager.acceptEvent(event.getId(), rishika); 
        }

        for(Event event : eventManager.getEventsForUser(graypacket))
            System.out.println(event);
        
        List<User> users = new ArrayList<>();
        users.add(graypacket);
        //users.add(rishika);
        //users.add(rajvar);
        for(Date[] freeSlot : eventManager.getFreeSlotsForUsers(users, new SimpleDateFormat(DAY_FORMAT).parse("25-10-2021"))) {
            System.out.println(freeSlot[0] + " to " + freeSlot[1]);
        }
    }
}
