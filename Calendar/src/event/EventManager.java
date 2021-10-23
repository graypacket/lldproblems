package event;
import java.util.HashMap;
import java.util.Map;

import user.User;

import java.util.List;
import java.util.ArrayList;

public class EventManager {
    private int autoIncrementingEventId = 0;
    private Map<Integer, Event> events = new HashMap<>();
    private Map<User, List<Event>> userToEventsMapping = new HashMap<>();

    public Event getEvent(int eventId) {
        return events.get(eventId);
    }

    public List<Event> getEventsForUser(User user) {
        List<Event> events; 
        return (events = userToEventsMapping.get(user)) == null ? new ArrayList<>() : events;
    }

    public Event createEvent(User user, String title) {
        Event event = new Event(++autoIncrementingEventId, user, title);
        events.put(event.getId(), event);

        if(!userToEventsMapping.containsKey(user))
            userToEventsMapping.put(user, new ArrayList<>());

        userToEventsMapping.get(user).add(event);
        return event;
    }

    public void removeEvent(int eventId) {
        events.remove(eventId);
    }

    public void addGuest(int eventId, User guest) {
        if(!events.containsKey(eventId)) return;
        
        Event event = events.get(eventId);
        event.addGuest(guest);

        if(!userToEventsMapping.containsKey(guest))
            userToEventsMapping.put(guest, new ArrayList<>());

        userToEventsMapping.get(guest).add(event);
    }

    public void acceptEvent(int eventId, User user) {
        if(!events.containsKey(eventId)) return;
        Event event = events.get(eventId);
        event.acceptEvent(user);
    }

    public void rejectEvent(int eventId, User user) {
        if(!events.containsKey(eventId)) return;
        Event event = events.get(eventId);
        event.rejectEvent(user);
    }
}
