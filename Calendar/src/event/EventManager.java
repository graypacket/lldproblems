package event;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import user.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

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
        if(event.getGuests().contains(user))
            event.acceptEvent(user);
    }

    public void rejectEvent(int eventId, User user) {
        if(!events.containsKey(eventId)) return;
        Event event = events.get(eventId);
        if(event.getGuests().contains(user))
            event.rejectEvent(user);
    }

    public List<Date[]> getFreeSlotsForUsers(List<User> users, Date date) {
        List<Date[]> freeSlots = new ArrayList<>();
        
        PriorityQueue<Entry<Date, Boolean>> timeline = new PriorityQueue<>(new Comparator<Entry<Date, Boolean>>(){
            @Override
            public int compare(Entry<Date, Boolean> pointInTimeline1, Entry<Date, Boolean> pointInTimeline2) {
                return pointInTimeline1.getKey().compareTo(pointInTimeline2.getKey());
            }
        });
        
        Date dayStart = new Date(date.getTime());
        Date dayEnd = new Date(date.getTime() + (24*3600 - 60)*1000);
        timeline.add(new SimpleEntry<>(dayStart, true));
        timeline.add(new SimpleEntry<>(dayStart, false));
        timeline.add(new SimpleEntry<>(dayEnd, true));
        timeline.add(new SimpleEntry<>(dayEnd, false));
        
        
        for(User user : users) {
            for(Event event : getEventsForUser(user)) {
                if(event.getStart().compareTo(dayStart) > 0 && event.getStart().compareTo(dayEnd) < 0)
                    timeline.add(new SimpleEntry<>(event.getStart(), true));
                if(event.getEnd().compareTo(dayStart) > 0 && event.getEnd().compareTo(dayEnd) < 0)
                    timeline.add(new SimpleEntry<>(event.getEnd(), false));
            }
        }

        Date freeSlotStart = null;
        Date freeSlotEnd = null;
        int numUsersOccupied = 0;
        while(!timeline.isEmpty()) {
            Entry<Date, Boolean> pointInTimeline = timeline.poll();
            if(pointInTimeline.getValue()) {
                numUsersOccupied++;
                if(freeSlotStart != null) {
                    freeSlotEnd = pointInTimeline.getKey();
                    freeSlots.add(new Date[]{freeSlotStart, freeSlotEnd});
                    freeSlotStart = null;
                    freeSlotEnd = null;
                }    
            }
            else numUsersOccupied--;

            if(numUsersOccupied == 0) {
                freeSlotStart = pointInTimeline.getKey();
            }
        }

        return freeSlots;
    }

    public void setEventStart(int id, Date date) {
        events.get(id).setStart(date);
    }

    public void setEventEnd(int id, Date date) {
        events.get(id).setEnd(date);
    }
}
