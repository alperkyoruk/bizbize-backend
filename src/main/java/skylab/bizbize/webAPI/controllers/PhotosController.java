package skylab.bizbize.webAPI.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skylab.bizbize.business.abstracts.PhotoService;
import skylab.bizbize.core.utilities.result.DataResult;
import skylab.bizbize.core.utilities.result.Result;
import skylab.bizbize.entities.Photo;

import java.util.List;

@RestController
@RequestMapping("api/photos")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PhotosController {
    private PhotoService photoService;

    @Autowired
    public PhotosController(PhotoService photoService){
        this.photoService = photoService;
    }

    @PostMapping("/add")
    public Result addPhoto(@RequestBody Photo photo){
        return photoService.addPhoto(photo);
    }

    @GetMapping("/getPhotoById")
    public Result getPhotoById(@RequestParam int photoId){
        return photoService.getPhotoById(photoId);
    }

    @GetMapping("/getPhotosByEventId")
    public DataResult<List<Photo>> getPhotosByEventId(@RequestParam int eventId){
        return photoService.getPhotosByEventId(eventId);
    }
}
