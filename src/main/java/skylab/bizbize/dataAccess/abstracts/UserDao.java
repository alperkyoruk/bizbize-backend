package skylab.bizbize.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import skylab.bizbize.entities.User;

public interface UserDao extends JpaRepository<User,Integer> {
    User findById(int id);

    User findByUsername(String username);


}
