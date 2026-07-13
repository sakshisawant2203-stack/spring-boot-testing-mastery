package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.repository.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,5,8"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected,a+b);
    }
}