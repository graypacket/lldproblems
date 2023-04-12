# Design a Calendar Application

The problem statement can be found at /low-level-design/problem/calendar.

## Design Diagram

You can find the design diagram Calendar.jpg in the current directory.

## Approach

We have User and Event class to represent users and events and their respective Manager classes, UserManager and EventManager to provide access to User and Event classes and perform operations like creating, updating and deleting users and events. Direct access to User and Event class methods is prevented by marking the methods as "protected", so that only the respective Manager classes can be used for write operations.

The main challenge in this problem is to provide the feature to find free slots for a group of users and the ability to create recurring events.

### Finding free slots

To find free slots for a group of users, we are adding start and end of every Event in a priority queue (min heap). Then we process the queue and if it's a start of an Event we increase a counter and if it's the end of an Event we decrease the counter. If the counter hits zero, we can mark the start of a free slot. The end can be marked when the counter becomes non zero. 

You can check the implementation getFreeSlotsForUsers() in EventManager.java.

### Recurring events

We have an enum EventRepeatFrequency to create Daily, Weekly, Monthly and Yearly repeating events. We can create an Event by providing the EventRepeatFrequency which will be checked while listing events for a user for a given day, i.e. in EventManager::getEventsForUser(User, Day). To list down the events for the day, we go through all the events for the user and compare the given day epoch to the start of the Event. Epoch is used to represent time as the number of seconds or ms elapsed since Jan 1, 1970. 
If the repeating frequency is Daily, (given_day_epoch_ms - event_start_epoch_ms) % ms_in_a_day would be zero. 
If the repeating frequency is Weekly, we can compare the week day of the Event start with the week day of the give day.
Similarily, if the repeating frequency is Monthly, we can compare the day number of the Event start date month with the day number of the given date month.
For Yearly frequency, we have to compare the day number as well as the month of the Event start and the given date.

You can run Calendar::main() method to play around with the code.