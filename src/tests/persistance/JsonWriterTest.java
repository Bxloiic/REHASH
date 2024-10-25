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

public class JsonWriterTest {
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
    private static final String JSON_STORE = "./data/JsonWriterTest.json";

    @BeforeEach
    void runBefore() throws IOException {
        hashdexes = new ArrayList<>();
        hashes = new ArrayList<>();
        outfits = new ArrayList<>();

        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
    }

    @Test
    void testWriteInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        try {
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteGeneralRehash() throws IOException {
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
    void testWriteEmptyRehash() throws IOException {
        // Write empty lists to JSON file
        writer.open();
        writer.write(hashes, hashdexes, outfits);
        writer.close();

        assertTrue(new File(JSON_STORE).exists());

        // Read data back and check it is empty
        assertEquals(0, ((List<Hash>) reader.read().get("hashes")).size());
        assertEquals(0, ((List<Hashdex>) reader.read().get("hashdexes")).size());
        assertEquals(0, ((List<Outfit>) reader.read().get("outfits")).size());
    }

}
