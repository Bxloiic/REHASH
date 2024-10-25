package tests.persistance;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// imports
import main.model.*;
import main.persistance.JsonReader;
import main.persistance.JsonWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    // Fields
    // Attributes
    private Hashdex testdex;
    private Hash testh;
    private Hash testh1;
    private Outfit fit;
    private List<Hash> hashes;
    private List<Hashdex> hashdexes;
    private List<Outfit> outfits;
    private Map<String, Object> data;
    private JsonReader reader;
    private JsonWriter writer;
    private static final String JSON_STORE = "./data/JsonReaderTest.json";

    @BeforeEach
    void runBefore() throws IOException {
        hashdexes = new ArrayList<>();
        hashes = new ArrayList<>();
        outfits = new ArrayList<>();

        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            data = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralRehash() throws IOException {
        // sample data
        testh = new Hash("BB Belt", "Belt", "Black", "Leather");
        testh1 = new Hash("K Kicks", "Moon Boots", "Black", "Fur");
        hashes.add(testh);
        hashes.add(testh1);

        testdex = new Hashdex("Hamptons Chic", "Blackish");
        testdex.addHash(testh);
        hashdexes.add(testdex);

        fit = new Outfit("Kendall Aspin Fit");
        fit.addHash(testh);
        fit.addHash(testh1);
        outfits.add(fit);

        // Write sample data to JSON file
        writer.open();
        writer.write(hashes, hashdexes, outfits);
        writer.close();

        // reader test
        Map<String, Object> data = reader.read();

        assertEquals(2, ((List<Hash>) data.get("hashes")).size());
        assertEquals("BB Belt", ((List<Hash>) data.get("hashes")).get(0).getName());
        assertEquals("K Kicks", ((List<Hash>) data.get("hashes")).get(1).getName());

        assertEquals(1, ((List<Hashdex>) data.get("hashdexes")).size());
        assertEquals("Hamptons Chic", ((List<Hashdex>) data.get("hashdexes")).get(0).getName());

        assertEquals(1, ((List<Outfit>) data.get("outfits")).size());
        assertEquals("Kendall Aspin Fit", ((List<Outfit>) data.get("outfits")).get(0).getName());
    }

    @Test
    void testReaderEmptyRehash() throws IOException {
        // Write empty lists to JSON file
        writer.open();
        writer.write(hashes, hashdexes, outfits);
        writer.close();

        Map<String, Object> data = reader.read();

        assertEquals(0, ((List<Hash>) data.get("hashes")).size());
        assertTrue(((List<Hash>) data.get("hashes")).isEmpty());

        // assertEquals(0, ((List<Hashdex>) data.get("hashdexes")).size());
        assertTrue(((List<Hashdex>) data.get("hashdexes")).isEmpty());

        assertEquals(0, ((List<Outfit>) data.get("outfits")).size());
        assertTrue(((List<Outfit>) data.get("outfits")).isEmpty());
    }

}
