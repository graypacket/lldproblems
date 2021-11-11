import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.ConsoleInputReader;

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
        ConsoleInputReader inputScanner = new ConsoleInputReader();
        while(inputScanner.hasNextLine("Command")) {
            String input = inputScanner.nextLine("");
            try {
                switch(input.strip()) {
                    case "create_user":
                        userManager.createUser(inputScanner.nextLine("First name"), inputScanner.nextLine("Last name"), inputScanner.nextLine("Username"));
                        break;
                    case "list_users":
                        for(User user : userManager.getUsers())
                            System.out.println(user);
                        break;
                    case "create_event":
                        User user = userManager.getUser(inputScanner.nextLine("Host's username"));
                        if(user == null) {
                            System.out.println("User not found!");
                            break;
                        }
                        String title = inputScanner.nextLine("Title");
                        String description = inputScanner.nextLine("Description");
                        
                        Date start = readDate(inputScanner.nextLine(String.format("Start (%s)", "dd-mm-yyyy hh:mm")), DATE_TIME_FORMAT);
                        
                        Date end = readDate(inputScanner.nextLine(String.format("End (%s)", "dd-mm-yyyy hh:mm")), DATE_TIME_FORMAT);
                        
                        String location = inputScanner.nextLine("Location");

                        int numGuests = Integer.valueOf(inputScanner.nextLine("Number of guests"));
                        Set<User> guests = new HashSet<>();
                        while(numGuests > 0) {
                            User guest = userManager.getUser(inputScanner.nextLine("Guest's username"));
                            if(guest == null) {
                                System.out.println("Guest not found!");
                            } else {
                                guests.add(guest);
                                numGuests--;
                            }
                        }
                        eventManager.createEvent(user, title, description, start, end, location, guests);
                        break;
                    case "list_events":
                        for(Event event : eventManager.getEvents())
                            System.out.println(event);
                        break;
                    case "accept_event":
                        eventManager.acceptEvent((int) Integer.valueOf(inputScanner.nextLine("Event id")), userManager.getUser(inputScanner.nextLine("Guest's username")));
                        break;
                    case "reject_event":
                        eventManager.rejectEvent((int) Integer.valueOf(inputScanner.nextLine("Event id")), userManager.getUser(inputScanner.nextLine("Guest's username")));
                        break;
                    case "list_events_for_user":
                        List<Event> events = eventManager.getEventsForUser(userManager.getUser(inputScanner.nextLine("username")));
                        for(Event event : events)
                            System.out.println(event);
                        break;
                    case "describe_event":
                        Event event = eventManager.getEvent((int) Integer.valueOf(inputScanner.nextLine("Event id")));
                        System.out.println(event);
                        break;
                    case "get_free_slots":
                        int numUsers = Integer.valueOf(inputScanner.nextLine("Num users"));
                        List<User> users = new ArrayList<>();
                        while(numUsers-- > 0) {
                            User user_ = userManager.getUser(inputScanner.nextLine("username"));
                            if(user_ != null)
                                users.add(user_);
                        }
                        Date date = readDate(inputScanner.nextLine(String.format("Date (%s)", "dd-mm-yyyy")), DAY_FORMAT);
                        
                        List<Date[]> slots = eventManager.getFreeSlotsForUsers(users, date);
                        for(Date[] slot : slots) {
                            System.out.println(String.format("%s to %s", slot[0], slot[1]));
                        }
                        break;
                    default:
                        System.out.println("INVALID CMD!");
                }
            } catch(Exception e) {
                System.out.println("INVALID CMD!");
                e.printStackTrace();
            }
        }
        inputScanner.close();
    }

    private static Date readDate(String inputDate, String format) throws ParseException {
        Date date = null;
        if(inputDate.isEmpty()) {
            inputDate = new SimpleDateFormat(format).format(new Date());
        }
        date = new SimpleDateFormat(format).parse(inputDate);  
        return date;
    }
}
