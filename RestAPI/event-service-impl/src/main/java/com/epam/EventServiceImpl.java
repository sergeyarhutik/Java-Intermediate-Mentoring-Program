package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("EventService")
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    @Override
    public Event updateEvent(Event updatedEvent) {
        if(eventRepository.existsById(updatedEvent.getId())) {
            return eventRepository.save(updatedEvent);
        }
        else {
            return null;
        }
    }

    @Override
    public Event getEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Failed to get Event by id: " + eventId));
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findAllByTitle(title);
    }
}