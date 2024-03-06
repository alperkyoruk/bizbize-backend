package skylab.bizbize.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import skylab.bizbize.core.utilities.result.*;
import skylab.bizbize.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import skylab.bizbize.business.abstracts.UserService;
import skylab.bizbize.dataAccess.abstracts.UserDao;
import skylab.bizbize.business.constants.Messages;

import java.util.List;

@Service
public class UserManager implements UserService, UserDetailsService {
    private UserDao userDao;

    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserManager(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setAuthorities();

        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);


        userDao.save(user);
        return new SuccessResult("User added successfully");
    }

    @Override
    public DataResult<List<User>> getUsers() {
        return new SuccessDataResult<List<User>>(userDao.findAll(), "Users listed successfully");
    }


    @Override
    public DataResult<User> getByUsername(String username) {
        var result = userDao.findByUsername(username);
        if (result == null) {
            return new ErrorDataResult<User>(Messages.userDoesntExist);
        }

        return new SuccessDataResult<User>(result, Messages.getUserByUsernameSuccess);
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = getByUsername(username).getData();
        return user;
    }


}
