package skylab.bizbize.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skylab.bizbize.business.abstracts.PhotoService;
import skylab.bizbize.business.constants.Messages;
import skylab.bizbize.core.utilities.result.*;
import skylab.bizbize.dataAccess.abstracts.PhotoDao;
import skylab.bizbize.entities.Photo;

import java.util.List;

@Service
public class PhotoManager implements PhotoService {

    private PhotoDao photoDao;

    @Autowired
    public PhotoManager(PhotoDao photoDao){
        this.photoDao = photoDao;
    }




    @Override
    public Result addPhoto(Photo photo) {
        if(photo.getPhotoUrl().isEmpty()){
            return new ErrorResult(Messages.photoUrlCannotBeNull);
        }

        photoDao.save(photo);
        return new SuccessResult(Messages.photoAddSuccess);
    }

    @Override
    public DataResult<Photo> getPhotoById(int id) {
        var result = photoDao.findById(id);
        if(result == null){
            return new SuccessDataResult<Photo>(Messages.photoDoesntExist);
        }
        return new SuccessDataResult<Photo>(result, Messages.getPhotoByIdSuccess);
    }

    @Override
    public DataResult<List<Photo>> getPhotosByEventId(int eventId) {
        var result = photoDao.findAllById(eventId);
        if(result == null){
            return new SuccessDataResult<List<Photo>>(Messages.getPhotosByEventIdIsEmpty);
        }

    return new SuccessDataResult<List<Photo>>(result,Messages.getPhotosByEventIdIsSuccess);
    }



}
