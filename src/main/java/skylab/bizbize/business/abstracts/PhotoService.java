package skylab.bizbize.business.abstracts;

import skylab.bizbize.core.utilities.result.DataResult;
import skylab.bizbize.core.utilities.result.Result;
import skylab.bizbize.entities.Photo;

import java.util.List;

public interface PhotoService {
    Result addPhoto(Photo photo);

    DataResult<Photo> getPhotoById(int id);

    DataResult<List<Photo>> getPhotosByEventId(int eventId);
}
