
# REHASH 

REHASH is a **Java Desktop Application** designed to help users generate outfits and organize virtual closets. Through the explorer page, users can browse and collect various clothing items such as sweaters, crewnecks, shoes, and outfits, which are referred to as “*hashes*.” These items are then stored in a personal closets known as a “*hashdex's*.” Each hashdex functions as its own individual collection, and users can create multiple hashdexes with custom names, profile images, and tags.

REHASH is designed for anyone who seeks inspiration for outfits, whether it’s for daily wear, special events, or wardrobe planning for the week. It’s also for those who want to build collections as share their personal styles to others who can appreciate their creation. Target audience ranges from ages 12-35+, though anyone interested in fashion can benefit.

Although i’m a tech guy, I absolutely adore clothing and fashion. I believe that
 > clothing is a powerful form of self-expression, capable of offering a glimpse into someone’s personality from a single outfit. 

It’s an essential aspect of life as others always get a first impression from what you wear. Personally, I often struggle with planning outfits, despite spending alot of time thinking about them. I also like to plan my outfits of the week but have no place to save them. REHASH is a great tool that helps answer my desires as well as inspire others and build a community of those who appreciate a good fit.

### User Stories:

- As a user, I want to be able to explore and view a list of clothing items (hashes) on the explorer page.
- As a user, I want to be able to select a hash and add it to one of my personal collections (hashdex).
- As a user, I want to be able to create a new hashdex and customize it.
- As a user, I want to be able to view a list of all my created hashdexes.
- As a user, I want to be able to organize outfits for certain days of the week and view them in a calendar format.
- As a user, I want to be able to I wanted to be able to save my current **REHASH** state, including all created hashes, hashdexes, and planned outfits
- As a user, I want to be able to load my saved **REHASH** state
- As a user, I want to be able to clear my saved **REHASH** state


### Features:
- Explore different clothing styles
- Curate personalized hashdex's
- Generate new outfits based on your ***personal style***
- Save and organize outfits into a weekly calender

### Instructions for End User:
- You can generate a hash, a hashdex, or a outfit by clicking the buttons with the corresponding labels 
- You can add hashes to outfits or hashdexes, with their corresponding button
- You can save, load, or clear the state of my application by clicking save in the REHASH application
- You can view created hashes..etc, by click the view button in the tabs

### Phase 4: Task 2:
    Fri Nov 29 03:49:37 PST 2024
    Added Hash: Netta's to Hashdex: Bayonetta 4
    Fri Nov 29 03:49:55 PST 2024
    Added Hash: Nike 3000s to Outfit: Jeanne

### Phase 4: Task 3: 
Although I believe my current program turned out better than I imagined. There are a couple aspects I would change if given more time.
1. Interfaces: If given more time, I would increase the amount of interfaces I used. In my current implementation alot of my classes have methods that do similar things but have personalized implementations; resulting in repeitiveness of my code. I would create more interfaces to have more encapsulation, less repitivity, and increase the overall robustness of my code
2. Exceptions: Another thing I would add is making specialized exceptions for matters pretaining to my code. Although there are some good ones the java library gives you, creating personalized exceptions for a variety of scenarioes in your code can be quiet helpful when debugging. It's also a great skill to show off.
3. Abstract Classes: I would add an abstract class to my code. Some of my classes such as Outfit and Hashdex share a lot of the same methdos (addHashTo...). I could've used an abstract class to encapsulate these behaviours and avoid duplication.


### FAQ

The name REHASH is a play on the concept of reusing items and previous *hashes* create something new. Through the curation of personal hashdex's, users can give old media and ideas new life.
