package tests.event;


import main.ui.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event event;
    private Date date;

    @BeforeEach
    public void setup() {
        event = new Event("Hash liked: Zip Pumps");
        date = Calendar.getInstance().getTime();  // Capture the current time
    }

    @Test
    public void testEventCreation() {
        assertEquals("Hash liked: Zip Pumps", event.getDescription());
        assertEquals(date.toString(), event.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\nHash liked: Zip Pumps", event.toString());
    }


}
