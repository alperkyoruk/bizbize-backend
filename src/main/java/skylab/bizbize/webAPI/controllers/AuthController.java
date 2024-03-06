package skylab.bizbize.webAPI.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import skylab.bizbize.business.abstracts.UserService;
import skylab.bizbize.core.security.JwtService;
import skylab.bizbize.core.utilities.result.Result;
import skylab.bizbize.entities.User;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3000/signup","http://localhost:3000/login" })
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestParam String username, @RequestParam String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(username);
        }
        throw new UsernameNotFoundException("invalid username");
    }

    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
