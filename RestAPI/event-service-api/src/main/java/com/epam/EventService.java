package com.epam;

import java.util.List;

public interface EventService {
     Event createEvent(Event newEvent);
     Event updateEvent(Event updatedEvent);
     Event getEvent(Long eventId);
     void deleteEvent(Long eventId);
     List<Event> getAllEvents();
     List<Event> getAllEventsByTitle(String title);
}
