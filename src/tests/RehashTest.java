package tests;

import main.model.*; // imports all classes in the model package

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class RehashTest {

    // fields
    private Hash testHash;
    private Hash testHassh;
    private Hashdex testHashDex;
    private Week testWeek;
    private Outfit testOutfit;
    private Outfit testOutfit1;

    @BeforeEach
    void runBefore() {
        testHash = new Hash("Doc Martens", "boots", "black", "leather");
        testHashDex = new Hashdex("Formal Wear", "Black and White");
        testWeek = new Week();

        testOutfit = new Outfit("going-out", testHash);
        testOutfit1 = new Outfit("fit", testHassh);

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
    void testAddMultiHash() {
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
    void testRemoveHash() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");

        testHashDex.addHash(hash);
        testHashDex.addHash(hassh);
        testHashDex.removeHash(); // removes most recently added item
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hash));
    }

    @Test
    void testRemoveMultiHash() {
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
    void testRemoveIndexHash() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        Hash hassh = new Hash("dress pants", "pants", "blue", "cotton");

        testHashDex.addHash(hash);
        testHashDex.addHash(hassh);
        testHashDex.removeHash(0); // removes item at specified index
        assertEquals(1, testHashDex.getHashListSize());
        assertTrue(testHashDex.getHashList().contains(hassh));
    }

    @Test
    void testRemoveIndexMultiHash() {
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
    void testSaveHash() {
        Hash hash = new Hash("Shirt", "Top", "White", "Cotton");
        hash.likedHash(); // Mark as liked

        testHashDex.saveHash(hash);
        assertEquals(1, testHashDex.getHashListSize());

        Hash hash2 = new Hash("Pants", "Bottom", "Black", "Denim");
        testHashDex.saveHash(hash2); // Not liked

        assertEquals(1, testHashDex.getHashListSize()); // Should not add second hash
    }

    @Test
    void testHashdexSetters() {
        testHashDex.setColour("green");
        testHashDex.setName("Paris Fashion");

        LinkedList<Hash> hashList = new LinkedList<>();
        Hash tHash = new Hash("X Earrings", "jewelery", "gold", "metal");
        hashList.add(testHash);
        hashList.add(tHash);
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

    // ------------ Outfit Class Tests ------------
    @Test
    void testOutfitConstructor() {
        ArrayList<Hash> outfitHash = new ArrayList<>();
        outfitHash.add(testHash); // adds hash to outfit arraylist

        testOutfit.setName("slay fit");
        assertEquals("slay fit", testOutfit.getName()); // tests to see if outfit name is being assigned correctly
        assertTrue(testOutfit.getHashs().contains(testHash)); // tests if hash is being added the string
        assertEquals(1, testOutfit.getHashs().size()); // tests if arraylist size (number of ekemnts) is correct
    }

    @Test
    void testOutfitSetter() {
        // new hash and araylist of hash to test
        Hash tHash = new Hash("X Earrings", "jewelery", "gold", "metal");
        ArrayList<Hash> oHash = new ArrayList<>();
        oHash.add(tHash);

        assertEquals("going-out", testOutfit.getName()); // tests to see if outfit name is being assigned correctly
        assertTrue(testOutfit.getHashs().contains(testHash)); // tests if hash is being added the string
        assertEquals(1, testOutfit.getHashs().size()); // tests if arraylist size (number of ekemnts) is correct

        testOutfit.setHashs(oHash);
        assertTrue(testOutfit.getHashs().contains(tHash)); // tests if hash is being added the string
        assertEquals(1, testOutfit.getHashs().size()); // tests if arraylist size (number of ekemnts) is correct

    }

    @Test
    void testDisplayOutfit() {
        // expected string output
        String expected = "Outfit: going-out\n" + //
                " - Items: [Item: Doc Martens (boots), Color: black, Material: leather, Liked: false, Tags: []]";
        assertEquals(expected, testOutfit.toString());
    }



}
