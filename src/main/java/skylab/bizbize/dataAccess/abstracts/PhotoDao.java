package skylab.bizbize.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import skylab.bizbize.entities.Photo;

import java.util.List;

public interface PhotoDao extends JpaRepository<Photo, Integer> {
    Photo findById(int id);

    List<Photo> findAllById(int eventId);
}

