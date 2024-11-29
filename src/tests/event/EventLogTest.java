package tests.event;

import main.ui.Event;
import main.ui.EventLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Event e1;
    private Event e2;
    private Event e3;
    private EventLog eventLog;

    @BeforeEach
    public void setup() {
        
        e1 = new Event("Hash liked: Zip Pumps");
        e2 = new Event("Hash liked: Nike 3000s");
        e3 = new Event("Hash liked: CTRL love");
        eventLog = EventLog.getInstance();
        eventLog.clear();  
        eventLog.logEvent(e1);
        eventLog.logEvent(e2);
        eventLog.logEvent(e3);
    }

    @Test
    public void testLogEvent() {
        // Check that the events are added to the log
        List<Event> events = new ArrayList<>();
        for (Event e : eventLog) {
            events.add(e);
        }
        assertTrue(events.contains(e1));
        assertTrue(events.contains(e2));
        assertTrue(events.contains(e3));
    }


    @Test
    public void testClear() {
        // Clears the log and check if it contains the "Event log cleared." message
        eventLog.clear();
        List<Event> events = new ArrayList<>();
        for (Event e : eventLog) {
            events.add(e);
        }
        assertEquals(1, events.size());
        assertEquals("Event log cleared.", events.get(0).getDescription());
    }
}
