package skylab.bizbize.webAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skylab.bizbize.business.abstracts.EventService;
import skylab.bizbize.core.utilities.result.DataResult;
import skylab.bizbize.core.utilities.result.Result;
import skylab.bizbize.entities.Event;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventsController {
    private EventService eventService;

    @Autowired
    public EventsController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/add")
    public Result addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @GetMapping("/getEvents")
    public DataResult<List<Event>> getEvents(){
        return eventService.getEvents();
    }

    @GetMapping("/getEventById")
    public DataResult<Event> getDepartmentById(@RequestParam int eventId){
        return eventService.getEventById(eventId);
    }

}

