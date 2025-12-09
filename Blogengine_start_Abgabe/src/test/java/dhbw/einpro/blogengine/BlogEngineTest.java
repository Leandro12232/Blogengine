package dhbw.einpro.blogengine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dhbw.einpro.blogengine.exceptions.*;
import dhbw.einpro.blogengine.impl.*;
import dhbw.einpro.blogengine.interfaces.*;

/**
 * JUnit Tests für die BlogEngine Anwendung.
 * Format: AAA (Arrange, Act, Assert)
 */
public class BlogEngineTest {

    // --- 1. Test für die Klasse USER ---
    @Test
    void testUserEquality() {
        // Arrange (Vorbereiten)
        IUser user1 = new User("Max", "Mustermann", "max@test.de");
        IUser user2 = new User("Max", "Mustermann", "max@test.de"); // Gleiche Daten
        IUser user3 = new User("Erika", "Musterfrau", "erika@test.de"); // Andere Daten

        // Act & Assert (Prüfen)
        assertEquals(user1, user2, "Zwei User mit gleichen Daten sollten 'equal' sein.");
        assertNotEquals(user1, user3, "Unterschiedliche User sollten nicht 'equal' sein.");
    }

    // --- 2. Test für die Klasse COMMENT ---
    @Test
    void testCommentLengthValidation() {
        // Arrange
        IUser author = new User("Tom", "Tester", "tom@test.de");
        IComment comment = new Comment("Kurzer Text", author);

        // String mit 260 Zeichen erzeugen
        String tooLongText = "a".repeat(260);

        // Act & Assert
        assertThrows(IllegalOperationException.class, () -> {
            comment.setContent(tooLongText);
        }, "Ein Kommentar mit mehr als 256 Zeichen muss eine Exception werfen.");
    }

    // --- 3. Test für die Klasse POST ---
    @Test
    void testPostScoreCalculation() throws Exception {
        // Arrange
        IBlogEngine engine = new BlogEngine();
        IUser author = new User("Autor", "Schreiber", "autor@mail.de");
        IUser liker1 = new User("Liker", "Eins", "liker1@mail.de");
        IUser liker2 = new User("Liker", "Zwei", "liker2@mail.de");
        IUser hater = new User("Hater", "Eins", "hater@mail.de");

        engine.addUser(author);
        engine.addUser(liker1);
        engine.addUser(liker2);
        engine.addUser(hater);

        IPost post = new Post("Mein Post", "Inhalt", author, engine);
        engine.addPost(post);

        // Act
        post.like(liker1);
        post.like(liker2);
        post.disLike(hater);

        // Assert: (2 Likes - 1 Dislike) * 10 = 10
        assertEquals(10, post.getScore(), "Score Berechnung ist falsch.");
    }

    // --- 4. Test für die Klasse BLOGENGINE ---
    @Test
    void testAddPostGeneratesId() throws Exception {
        // Arrange
        IBlogEngine engine = new BlogEngine();
        IUser author = new User("Susi", "Sorglos", "susi@mail.de");
        engine.addUser(author);

        IPost post1 = new Post("Titel 1", "Inhalt 1", author, engine);
        IPost post2 = new Post("Titel 2", "Inhalt 2", author, engine);

        // Act
        int id1 = engine.addPost(post1);
        int id2 = engine.addPost(post2);

        // Assert
        assertEquals(1, id1, "Erste ID muss 1 sein.");
        assertEquals(2, id2, "Zweite ID muss 2 sein.");
        assertEquals(2, engine.getPosts().size(), "Anzahl der Posts stimmt nicht.");
    }
}
