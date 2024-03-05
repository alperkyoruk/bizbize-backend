package skylab.bizbize.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skylab.bizbize.business.abstracts.EventService;
import skylab.bizbize.business.constants.Messages;
import skylab.bizbize.core.utilities.result.*;
import skylab.bizbize.dataAccess.abstracts.EventDao;
import skylab.bizbize.entities.Event;
import skylab.bizbize.entities.Photo;

import java.util.List;

@Service
public class EventManager implements EventService {

    private EventDao eventDao;

    @Autowired
    public EventManager(EventDao eventdao){
        this.eventDao = eventdao;
    }


    @Override
    public Result addEvent(Event event) {
        if(event.getGuestName().isEmpty()){
            return new ErrorResult(Messages.eventNameCannotBeNull);
        }
        for (Photo photo : event.getPhotos()) {
            photo.setEvent(event); // Set the event reference for each photo
        }

        eventDao.save(event);
        return new SuccessResult(Messages.eventAddSuccess);
    }

    @Override
    public DataResult<List<Event>> getEvents() {
        var result = eventDao.findAll();
        if(result.isEmpty()){
            return new SuccessDataResult<List<Event>>(Messages.getEventsIsEmpty);
        }
        return new SuccessDataResult<List<Event>>(result,Messages.getEventsSuccess);
    }

    @Override
    public DataResult<Event> getEventById(int id) {
        var result = eventDao.findById(id);
        if(result == null){
            return new SuccessDataResult<Event>(Messages.eventDoesntExist);
        }

        return new SuccessDataResult<Event>(result, Messages.getEventByIdSuccess);
    }

    @Override
    public DataResult<Event> getActiveEvent(int id) {
        var result = eventDao.findByIsActive(id);
        if(result == null){
            return new SuccessDataResult<Event>(Messages.eventDoesntExist);
        }


        return new SuccessDataResult<Event>(result, Messages.getEventByIdSuccess);
    }
}
