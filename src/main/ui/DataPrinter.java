package main.ui;

import main.ui.*;
import main.model.*;
import java.util.List;
import java.util.Map;

public class DataPrinter {
    // EFFECTS: prints the loaded data to the console
    public static void printLoadedData(Map<String, Object> data) {
        System.out.println("=== Loaded Data ===");

        // Print hashes
        List<Hash> hashes = (List<Hash>) data.get("hashes");
        System.out.println("Hashes:");
        for (Hash hash : hashes) {
            System.out.printf("  - Name: %s, Type: %s, Colour: %s, Material: %s%n",
                    hash.getName(), hash.getType(), hash.getColour(), hash.getMaterial());
        }

        // Print hashdexes
        List<Hashdex> hashdexes = (List<Hashdex>) data.get("hashdexes");
        System.out.println("Hashdexes:");
        for (Hashdex hashdex : hashdexes) {
            System.out.printf("  - Name: %s, Colour: %s%n", hashdex.getName(), hashdex.getColour());
            System.out.println("    Hashes:");
            for (Hash hash : hashdex.getHashList()) {
                System.out.printf("      * Name: %s, Type: %s, Colour: %s, Material: %s%n",
                        hash.getName(), hash.getType(), hash.getColour(), hash.getMaterial());
            }
        }

        // Print outfits
        List<Outfit> outfits = (List<Outfit>) data.get("outfits");
        System.out.println("Outfits:");
        for (Outfit outfit : outfits) {
            System.out.printf("  - Name: %s%n", outfit.getName());
            System.out.println("    Hashes:");
            for (Hash hash : outfit.getOutfitHashs()) {
                System.out.printf("      * Name: %s, Type: %s, Colour: %s, Material: %s%n",
                        hash.getName(), hash.getType(), hash.getColour(), hash.getMaterial());
            }
        }
    }
}

