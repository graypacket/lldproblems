package event;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import user.User;

public class Event {
    private int eventId;
    private String title = "";
    private String description = "";
    private Date start = new Date();
    private Date end = new Date();
    private String location = "";
    private User owner = null;
    private EventRepeatFrequency repeatFrequency;
    private List<User> guests = new ArrayList<>();
    private List<User> guestsAccepted = new ArrayList<>();
    private List<User> guestsRejected = new ArrayList<>();
    
    protected Event(int eventId, User owner, String title) {
        this.eventId = eventId;
        this.owner = owner;
        this.title = title;
    }

    protected Event(int eventId, User owner, String title, String description, Date start, Date end, String location, List<User> guests, EventRepeatFrequency repeatFrequency) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.start = start;
        if(end.compareTo(start) < 0) end = start;
        this.end = end;
        this.location = location;
        this.owner = owner;
        this.guests = guests;
        this.repeatFrequency = repeatFrequency;
    }

    public int getId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    protected void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        if(start.compareTo(end) > 0) return start;
        return end;
    }

    protected void setEnd(Date end) {
        if(start.compareTo(end) > 0) return;
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    public EventRepeatFrequency getRepeatFrequency() {
        return repeatFrequency;
    }

    protected void setRepeatFrequency(EventRepeatFrequency repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    protected void addGuest(User guest) {
        System.out.println(guest.getUsername() + guest);
        System.out.println(owner.getUsername() + owner);
        if(guest != null && guest != owner)
            guests.add(guest);
    }

    protected void removeGuest(User guest) {
        if(guest != null)
            guests.remove(guest);
    }

    public User getOwner() {
        return owner;
    }

    public String toString() {
        StringBuilder event = new StringBuilder(String.format("id: %d, title: %s, description: %s, start: %s, end: %s, location: %s, owner: %s, repeat: %s, guests: ", eventId, title, description, start, end, location, owner.getUsername(), repeatFrequency));

        for(User guest : guests) {
            event.append(guest.getUsername() + ", ");
        }
        if(guests.size() != 0)
            event.delete(event.length()-2, event.length());

        event.append(", accepted: ");

        for(User guest: guestsAccepted) {
            event.append(guest.getUsername() + ", ");
        }
        if(guestsAccepted.size() != 0)
            event.delete(event.length()-2, event.length());

        event.append(", rejected: ");

        for(User guest: guestsRejected) {
            event.append(guest.getUsername() + ", ");
        }
        if(guestsRejected.size() != 0)
            event.delete(event.length()-2, event.length());

        return event.toString();
    }

    public void acceptEvent(User user) {
        guestsRejected.remove(user);
        guestsAccepted.add(user);
    }

    public void rejectEvent(User user) {
        guestsAccepted.remove(user);
        guestsRejected.add(user);
    }

    public List<User> getGuests() {
        return guests;
    }
}