
// now here we going to write our business logic,and will use JournalEntryService in controller package.
package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.entity.JournalEntry;
import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component  // add @Component here so it doesn't found null and spring will create object of this.
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository JournalEntryRepository;  // we are inject this  JournalEntryRepository in JournalEntryServices class.


    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) { // we want to save this.
        User user = userService.findByUserName(username);
        // now saving  Date in journalEntry
        journalEntry.setDate(LocalDateTime.now());
        // now to the next line(JournalEntryRepository.save(journalEntry)) is saving the journalEntry in database we are seving  this  in local variable(saved) using saved by just pressing alt + enter
        JournalEntry saved = JournalEntryRepository.save(journalEntry);
        // we have find user already now our main goal to adding this entry into user journalEntries
        user.getJournalEntries().add(saved);
        // now user is in memory so have to call userServices again saving this user into database
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry) { // we want to save this.
        JournalEntryRepository.save(journalEntry);     // this saveEntry method is for connection between users and JournalEntry
    }

    public List<JournalEntry> getAll() {
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return JournalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                JournalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {

            throw new RuntimeException("an error occured while deleting the entry", e);
        }
        return removed;
    }
}
// controller call to service and service call to repository
// so i got error on JournalEntry so i just clicked on JournalEntry and click on import class.

