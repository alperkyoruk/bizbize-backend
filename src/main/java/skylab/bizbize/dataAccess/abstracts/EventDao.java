package skylab.bizbize.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import skylab.bizbize.entities.Event;

public interface EventDao extends JpaRepository<Event, Integer> {
    Event findById(int id);

}
