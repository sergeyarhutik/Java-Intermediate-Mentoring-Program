package com.epam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "Event REST")
public class EventServiceController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/")
    @ApiOperation("Redirects to swagger-ui")
    @ResponseStatus(value = HttpStatus.OK)
    public void getApi(HttpServletResponse response) throws IOException {
        System.out.println("LOG: getApi -> SWAGGER-UI ");
        response.sendRedirect("/swagger-ui/");
    }

    @PostMapping(value = "/events")
    @ApiOperation("Create new event")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createEvent(@RequestBody Event newEvent) {
        System.out.println("LOG: addEvent: " + newEvent);
        eventService.createEvent(newEvent);
    }

    @PutMapping(value = "/events")
    @ApiOperation("Update event by id")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateEvent(@RequestBody Event newEvent) {
        System.out.println("LOG: updateEvent: " + newEvent);
        eventService.updateEvent(newEvent);
    }

    @GetMapping(value = "/events/{eventId}")
    @ApiOperation("Get event by id")
    @ResponseStatus(value = HttpStatus.FOUND)
    public Event getEvent(@PathVariable("eventId") Long eventId) {
        System.out.println("LOG: getEvent by id: " + eventId);
        return eventService.getEvent(eventId);
    }

    @DeleteMapping(value = "/events/{eventId}")
    @ApiOperation("Delete event by id")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        System.out.println("LOG: deleteEvent by id: " + eventId);
        eventService.deleteEvent(eventId);
    }

    @GetMapping(value = "/events")
    @ApiOperation("Get all events")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> getAllEvents() {
        System.out.println("LOG: getAllEvents()");
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/events/bytitle/{title}")
    @ApiOperation("Get event by {title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> getAllEventsByTitle(@PathVariable("title") String title) {
        System.out.println("LOG: getAllEventsByTitle(), title: " + title);
        return eventService.getAllEventsByTitle(title);
    }

}