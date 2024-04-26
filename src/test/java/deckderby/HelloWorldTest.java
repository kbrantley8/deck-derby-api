package deckderby;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HelloWorldTest {

    @BeforeEach
    public void setUp() { }

    @AfterEach
    public void tearDown() { }

    @Test
    public void testHelloWorld() {
        assertTrue(true);
    }

}
