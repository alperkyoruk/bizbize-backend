package skylab.bizbize.business.abstracts;

import skylab.bizbize.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import skylab.bizbize.core.utilities.result.DataResult;
import skylab.bizbize.core.utilities.result.Result;

import java.util.List;

public interface UserService extends UserDetailsService {
    Result addUser(User user);

    DataResult<List<User>> getUsers();

    DataResult<User> getByUsername(String username);

    UserDetails loadUserByUsername(String username);

}

