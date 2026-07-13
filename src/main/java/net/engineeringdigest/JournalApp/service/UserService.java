// now here we going to write our business logic,and will use JournalEntryService in controller package.
package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component  // add @Component here so it doesn't found null and spring will create object of this.
public class UserService {

    @Autowired
    private UserRepository userRepository;  // we are inject this  JournalEntryRepository in JournalEntryServices class.
     // here what actually happen userServices called userRepository

    @Autowired
    private PasswordEncoder passwordencoder;
    // private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();



    private final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    // each logger has its diff instant so make it private,to protect from accidental reassignment make it final,to JournalEntryService instant make 1 in
// LoggerFactory is utility class,to get a instance of logger we use getLogger & logger associated with any class so JournalEntryService.class

    public boolean saveNewEntry(User user) {  // we want to save this.
        try {
            user.setPassword(passwordencoder.encode(user.getPassword()));  // here we encode password and then setPassword
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.info("hahahaaaa");
            logger.error("hahahaaaa");
            logger.warn("hahahaaaa");
            logger.debug("hahahaaaa");
            logger.trace("hahahaaaa");
            return false;
        }
    }

   public void saveUser(User user){  // we want to save this.

        userRepository.save(user);
    }

    public List<User>getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String Username){
        return userRepository.findByusername(Username); // it just take username and passing to  UserRepository
        // where we already created this findByUserName method.
    }
}
// controller call to service and service call to repository
// so i got error on JournalEntry so i just clicked on JournalEntry and click on import class.