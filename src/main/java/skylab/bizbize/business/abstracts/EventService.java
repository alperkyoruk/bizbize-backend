package skylab.bizbize.business.abstracts;

import skylab.bizbize.core.utilities.result.DataResult;
import skylab.bizbize.core.utilities.result.Result;
import skylab.bizbize.entities.Event;

import java.util.List;

public interface EventService {

    Result addEvent(Event event);

    DataResult<List<Event>> getEvents();

    DataResult<Event> getEventById(int id);

  //  DataResult<Event> getActiveEvent(int id);

    DataResult<Event> getActiveEvent();

}
