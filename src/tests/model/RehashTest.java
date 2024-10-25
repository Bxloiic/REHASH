package tests.model;

import main.model.*; // imports all classes in the model package

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

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

        testOutfit = new Outfit("going-out");
        testOutfit1 = new Outfit("fit");

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
    void testSetName() {
        testHash.setName("New Name");
        assertEquals("New Name", testHash.getName());
    }

    @Test
    void testSetType() {
        testHash.setType("sneakers");
        assertEquals("sneakers", testHash.getType());
    }

    @Test
    void testSetColour() {
        testHash.setColour("red");
        assertEquals("red", testHash.getColour());
    }

    @Test
    void testSetMaterial() {
        testHash.setMaterial("suede");
        assertEquals("suede", testHash.getMaterial());
    }

    @Test
    void testSetTags() {
        List<String> newTags = Arrays.asList("gothic", "edgy");
        testHash.setTags(newTags);
        assertTrue(testHash.getTags().containsAll(newTags));
        assertEquals(2, testHash.getTags().size());
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
    void testHashAddDuplicateTag() {
        testHash.addTag("gothic");
        testHash.addTag("gothic"); // add duplicate
        assertEquals(1, testHash.getTags().size());
    }

    @Test
    void testHashRemoveNonExistentTag() {
        testHash.removeTag("non-existent");
        assertEquals(0, testHash.getTags().size());
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
    void testRemoveTagFromEmptyList() {
        testHash.removeTag("non-existent");
        assertEquals(0, testHash.getTags().size());
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
    void testAddHashToHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        testHashDex.addHash(hash);
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));
    }

    @Test
    void testAddMultiHashToHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");
        testHashDex.addHash(hash);
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));
        testHashDex.addHash(hassh);
        assertEquals(2, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hassh));

    }

    @Test
    void testRemoveHashFromHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");

        testHashDex.addHash(hash);
        testHashDex.addHash(hassh);
        testHashDex.removeHash(); // removes most recently added item
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));
    }

    @Test
    void testRemoveHashFromEmptyHashdex() {
        assertThrows(NoSuchElementException.class, () -> testHashDex.removeHash());
    }

    @Test
    void testRemoveMultiHashFromHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");

        testHashDex.addHash(hassh);
        testHashDex.addHash(hash);
        testHashDex.removeHash(); // removes most recently added item
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hassh));

        testHashDex.removeHash(); // removes most recently added item
        assertTrue(!(testHashDex.getHashList().contains(hassh)));
        assertTrue(!(testHashDex.getHashList().contains(hash)));
        assertEquals(0, testHashDex.getHashListSize());
    }

    @Test
    void testRemoveIndexHashFromHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");

        testHashDex.addHash(hash);
        testHashDex.addHash(hassh);
        testHashDex.removeHash(0); // removes item at specified index
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hassh));
    }

    @Test
    void testRemoveIndexMultiHashFromHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");

        testHashDex.addHash(hash);
        testHashDex.addHash(hassh);
        testHashDex.removeHash(1); // removes item at specified index
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));

        testHashDex.removeHash(0); // removes most recently added item
        assertTrue(!(testHashDex.getHashList().contains(hassh)));
        assertTrue(!(testHashDex.getHashList().contains(hash)));
        assertEquals(0, testHashDex.getHashListSize());
    }

    @Test
    void testSaveHashToHashdex() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        hash.likedHash(); // Mark as liked

        testHashDex.saveHash(hash);
        assertEquals(1, testHashDex.getHashListSize());

        Hash hash2 = new Hash("Pants", "Bottom", "Black", "Denim");
        testHashDex.saveHash(hash2); // Not liked

        assertEquals(1, testHashDex.getHashListSize()); // Should not add second hash
    }

    @Test
    void testRemoveHashFromEmptyList() {
        assertThrows(NoSuchElementException.class, () -> testHashDex.removeHash());
    }

    @Test
    void testRemoveHashAtInvalidIndex() {
        testHashDex.addHash(testHash);
        assertThrows(IndexOutOfBoundsException.class, () -> testHashDex.removeHash(2));
    }

    @Test
    void testHashdexSetters() {
        testHashDex.setColour("green");
        testHashDex.setName("Paris Fashion");

        LinkedList<Hash> hashList = new LinkedList<>();
        Hash thash = new Hash("X Earrings", "jewelery", "gold", "metal");
        hashList.add(testHash);
        hashList.add(thash);
        testHashDex.setLinkedList(hashList);

        assertEquals("green", testHashDex.getColour());
        assertEquals("Paris Fashion", testHashDex.getName());
        assertEquals(2, testHashDex.getHashListSize());
    }

    @Test
    void testHashdexGetters() {
        assertEquals("Formal Wear", testHashDex.getName());
        assertEquals("Black and White", testHashDex.getColour());
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

    // @Test
    // void testDisplayWeekWithNoOutfits() {
    // assertEquals("No outfits assigned for the week.", testWeek.displayWeek());
    // }

    // @Test
    // void testDisplayWeekWithAssignedOutfits() {
    // testWeek.addOutfit("Monday", testOutfit);
    // testWeek.addOutfit("Friday", testOutfit1);

    // String expected = "Monday: going-out\n" +
    // "Tuesday: No outfit assigned.\n" +
    // "Wednesday: No outfit assigned.\n" +
    // "Thursday: No outfit assigned.\n" +
    // "Friday: fit\n" +
    // "Saturday: No outfit assigned.\n" +
    // "Sunday: No outfit assigned.";

    // assertEquals(expected, testWeek.displayWeek());
    // }

    // ------------ Outfit Class Tests ------------
    @Test
    void testOutfitConstructor() {
        testOutfit = new Outfit("slay fit"); // Initialize with name only
        assertEquals("slay fit", testOutfit.getName());

        testOutfit.addHash(testHash); // Add a hash after instantiation
        assertTrue(testOutfit.getOutfitHashs().contains(testHash)); // Check if hash is added
        assertEquals(1, testOutfit.getOutfitHashs().size()); // Check if size is correct
    }

    @Test
    void testOutfitSetter() {
        Hash thash = new Hash("X Earrings", "jewelery", "gold", "metal");
        ArrayList<Hash> ohash = new ArrayList<>();
        ohash.add(thash);

        testOutfit.setOutfitHashs(ohash);
        assertTrue(testOutfit.getOutfitHashs().contains(thash));
        assertEquals(1, testOutfit.getOutfitHashs().size());
    }


}
