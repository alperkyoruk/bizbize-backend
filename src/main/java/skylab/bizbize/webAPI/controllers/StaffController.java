package skylab.bizbize.webAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skylab.bizbize.business.abstracts.StaffService;
import skylab.bizbize.core.utilities.result.DataResult;
import skylab.bizbize.core.utilities.result.Result;
import skylab.bizbize.entities.Staff;

import java.util.List;

@RestController
@RequestMapping("api/staff")
public class StaffController {
    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }

    @PostMapping("/add")
    public Result addStaff(@RequestBody Staff staff){
        return staffService.addStaff(staff);
    }

    @GetMapping("/getStaff")
    public DataResult<List<Staff>> getStaff(){
        return staffService.getStaffs();
    }

    @GetMapping("/getStaffById")
    public DataResult<Staff> getStaffById(@RequestParam int staffId){
        return staffService.getStaffById(staffId);
    }

    @GetMapping("/getStaffByFirstAndLastName")
    public DataResult<Staff> getStaffByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        return staffService.getStaffByFirstAndLastName(firstName, lastName);
    }



}
