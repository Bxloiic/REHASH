package tests;

import main.model.*; // imports all classes in the model package

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class RehashTest {

    // fields
    private Hash testHash;
    private Hashdex testHashDex;
    private Week testWeek;
    private Outfit testOutfit;
    private Outfit testOutfit1;

    @BeforeEach
    void runBefore() {
        testHash = new Hash("Doc Martens", "boots", "black", "leather");
        testHashDex = new Hashdex("Formal Wear", "Black and White");
        testWeek = new Week();

        List<String> hashs = Arrays.asList("Shirt", "Pants", "Shoes");
        List<String> outfit = Arrays.asList("Hat", "shirt", "Shoes");
        testOutfit = new Outfit("going-out", hashs);
        testOutfit1 = new Outfit("fit", outfit);

    }

    // ------------ Hash Class Tests ------------
    @Test
    void testHashConstructor() {
        assertEquals("Doc Martens", testHash.getName());
        assertEquals("boots", testHash.getType());
        assertEquals("black", testHash.getColour());
        assertEquals("leather", testHash.getMaterial());
        assertFalse(testHash.getLiked());
        assertEquals(0, testHash.getTags().size());
    }

    @Test
    void testHashLikedHash() {
        testHash.likedHash();
        assertTrue(testHash.getLiked());
    }

    @Test
    void testHashUnlikedHash() {
        assertFalse(testHash.getLiked());
        testHash.likedHash(); // makes true
        assertTrue(testHash.getLiked());
        testHash.unlikedHash(); // makes false
        assertFalse(testHash.getLiked());
    }

    @Test
    void testHashAddTags() {
        testHash.addTag("gothic");
        assertTrue(testHash.getTags().contains("gothic"));
        assertTrue(testHash.getTags().contains("gothic"));
        assertEquals(1, testHash.getTags().size());
    }

    @Test
    void testHashAddMultiTags() {
        testHash.addTag("gothic");
        testHash.addTag("emo");
        testHash.addTag("ravencore");
        assertTrue(testHash.getTags().contains("gothic"));
        assertTrue(testHash.getTags().contains("emo"));
        assertTrue(testHash.getTags().contains("ravencore"));
        assertEquals(3, testHash.getTags().size());
    }

    @Test
    void testHashRemoveTags() {
        testHash.addTag("gothic");
        testHash.addTag("emo");
        testHash.addTag("ravencore");
        testHash.removeTag();

        assertTrue(testHash.getTags().contains("gothic"));
        assertTrue(testHash.getTags().contains("emo"));
        assertEquals(2, testHash.getTags().size());
    }

    @Test
    void testHashRemoveMultiTags() {
        testHash.addTag("gothic");
        testHash.addTag("emo");
        testHash.addTag("ravencore");
        testHash.removeTag();

        assertTrue(testHash.getTags().contains("gothic"));
        assertTrue(testHash.getTags().contains("emo"));
        assertEquals(2, testHash.getTags().size());

        testHash.removeTag();
        assertTrue(testHash.getTags().contains("gothic"));
        assertEquals(1, testHash.getTags().size());
    }

    @Test
    void testHashRemoveIndexTags() {
        testHash.addTag("gothic");
        testHash.addTag("emo");
        testHash.addTag("ravencore");
        assertEquals(3, testHash.getTags().size());

        testHash.removeTag("gothic");
        assertTrue(testHash.getTags().contains("ravencore"));
        assertTrue(testHash.getTags().contains("emo"));
        assertEquals(2, testHash.getTags().size());
    }

    @Test
    void testHashRemoveIndexMultiTags() {
        testHash.addTag("gothic");
        testHash.addTag("emo");
        testHash.addTag("ravencore");
        assertEquals(3, testHash.getTags().size());

        testHash.removeTag("gothic");
        assertTrue(testHash.getTags().contains("ravencore"));
        assertTrue(testHash.getTags().contains("emo"));
        assertEquals(2, testHash.getTags().size());

        testHash.removeTag("emo");
        assertTrue(testHash.getTags().contains("ravencore"));
        assertEquals(1, testHash.getTags().size());
    }

    // ------------ Hashdex Class Tests ------------
    @Test
    void testHashdexConstructor() {
        assertEquals("Formal Wear", testHashDex.getName());
        assertEquals("Black and White", testHashDex.getColour());
        assertEquals(0, testHashDex.getHashListSize());
    }

    @Test
    void testAddHash() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        testHashDex.addHash(hash);
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));
    }

    @Test
    void AddMultiHash() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");
        testHashDex.addHash(hash);
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));
        testHashDex.addHash(hassh);
        assertEquals(2, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hassh));

    }

    

    // ------------ Week Class Tests ------------

    @Test
    void testWeekConstructor() {
        assertTrue(testWeek.getOutfits().isEmpty()); // checks if the outfits hashmap is empty
    }

    @Test
    void testAddOutfits() {
        testWeek.addOutfit("Monday", testOutfit);
        assertEquals(testOutfit.getName(), testWeek.getOutfits().get("Monday").getName()); // get's specified key from
                                                                                           // the outfit hashmap
        assertEquals(1, testWeek.getOutfits().size());
    }

    @Test
    void testAddMultiOutfits() {
        testWeek.addOutfit("Monday", testOutfit);
        testWeek.addOutfit("Friday", testOutfit1);
        assertEquals(testOutfit.getName(), testWeek.getOutfits().get("Monday").getName()); // get's specified key from
                                                                                           // the outfit hashmap
        assertEquals(testOutfit1.getName(), testWeek.getOutfits().get("Friday").getName()); // get's specified key from
                                                                                            // the outfit hashmap
        assertEquals(2, testWeek.getOutfits().size());
    }

    @Test
    void testRemoveOutfit() {
        testWeek.addOutfit("Monday", testOutfit);
        testWeek.removeOutfit("Monday");
        assertTrue(testWeek.getOutfits().isEmpty()); // checks if the outfits hashmap is empty
        assertEquals(0, testWeek.getOutfits().size());
    }

    @Test
    void testRemoveMultiOutfit() {
        testWeek.addOutfit("Monday", testOutfit);
        testWeek.addOutfit("Friday", testOutfit1);
        testWeek.removeOutfit("Friday");

        assertEquals(1, testWeek.getOutfits().size());

        testWeek.removeOutfit("Monday");
        assertTrue(testWeek.getOutfits().isEmpty()); // checks if the outfits hashmap is empty
        assertEquals(0, testWeek.getOutfits().size());
    }

}
