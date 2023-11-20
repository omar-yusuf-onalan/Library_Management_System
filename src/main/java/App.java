import Entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.type.descriptor.java.LocalDateJavaType;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Publisher noPublisher = receivePreparedPublisher("No publisher", "No publisher");
        entityManager.persist(noPublisher);

        Publisher egerton = receivePreparedPublisher("T. Egerton, Whitehall (London)", "Thomas Egerton was a bookseller and publisher in Whitehall, London.");
        entityManager.persist(egerton);

        Publisher russianMessenger = receivePreparedPublisher("The Russian Messenger", "The Russian Messenger or Russian Herald has been the title of three notable magazines published in Russia during the 19th century and early 20th century.");
        entityManager.persist(russianMessenger);

        Publisher chapman = receivePreparedPublisher("Chapman & Hall (London)", "Chapman & Hall is an imprint owned by CRC Press, originally founded as a British publishing house in London in the first half of the 19th century by Edward Chapman and William Hall.");
        entityManager.persist(chapman);

        Publisher whigReview = receivePreparedPublisher("The American Whig Review", "The American Review, alternatively known as The American Review: A Whig Journal and The American Whig Review, was a New York City-based monthly periodical that published from 1844 to 1852.");
        entityManager.persist(whigReview);

        transaction.commit();

        transaction.begin();

        Author shakespeare = receivePreparedAuthor("William Shakespeare", LocalDate.of(1564, 4, 23), "England");
        entityManager.persist(shakespeare);

        Author austen = receivePreparedAuthor("Jane Austen", LocalDate.of(1775, 12, 16), "United Kingdom");
        entityManager.persist(austen);

        Author tolstoy = receivePreparedAuthor("Leo Tolstoy", LocalDate.of(1828, 9, 9), "Russia");
        entityManager.persist(tolstoy);

        Author dickens = receivePreparedAuthor("Charles Dickens", LocalDate.of(1812, 2, 7), "England");
        entityManager.persist(dickens);

        Author poe = receivePreparedAuthor("Edgar Allan Poe", LocalDate.of(1809, 1, 19), "United States");
        entityManager.persist(poe);

        Book hamlet = receivePreparedBook(
                "Hamlet",
                1603,
                10,
                entityManager.find(Author.class, 1),
                entityManager.find(Publisher.class, 1)
        );
        entityManager.persist(hamlet);

        Book prideAndPrejudice = receivePreparedBook(
                "Pride and Prejudice",
                1813,
                10,
                entityManager.find(Author.class, 2),
                entityManager.find(Publisher.class, 2)
        );
        entityManager.persist(prideAndPrejudice);

        Book warAndPeace = receivePreparedBook(
                "War and Peace",
                1869,
                10,
                entityManager.find(Author.class, 3),
                entityManager.find(Publisher.class, 3)
        );
        entityManager.persist(warAndPeace);

        Book aTaleOfTwoCities = receivePreparedBook(
                "A Tale of Two Cities",
                1859,
                10,
                entityManager.find(Author.class, 4),
                entityManager.find(Publisher.class, 4)
        );
        entityManager.persist(aTaleOfTwoCities);

        Book theRavenAndOtherPoems = receivePreparedBook(
                "The Raven and Other Poems",
                1845,
                10,
                entityManager.find(Author.class, 5),
                entityManager.find(Publisher.class, 5)
        );
        entityManager.persist(theRavenAndOtherPoems);

        transaction.commit();
    }

    private static Author receivePreparedAuthor(String name, LocalDate birthdate, String country) {
        Author author = new Author();

        author.setName(name);
        author.setBirthdate(birthdate);
        author.setCountry(country);

        return author;
    }

    private static Publisher receivePreparedPublisher(String name, String description) {
        Publisher publisher = new Publisher();

        publisher.setName(name);
        publisher.setDescription(description);

        return publisher;
    }

    private static Book receivePreparedBook(String name, int publicationYear, int stock, Author author, Publisher publisher) {
        Book book = new Book();

        book.setName(name);
        book.setPublicationYear(publicationYear);
        book.setStock(stock);
        book.setAuthor(author);
        book.setPublisher(publisher);

        return book;
    }

    private static Category receivePreparedCategory(String name, String description) {
        Category category = new Category();

        category.setName(name);
        category.setDescription(description);

        return category;
    }

    private static BookBorrowing receivePreparedBookBorrowing(String borrowerName, LocalDate borrowingDate, LocalDate returnDate, Book book) {
        BookBorrowing bookBorrowing = new BookBorrowing();

        bookBorrowing.setBorrowerName(borrowerName);
        bookBorrowing.setBorrowingDate(borrowingDate);
        bookBorrowing.setReturnDate(returnDate);
        bookBorrowing.setBook(book);

        return bookBorrowing;
    }
}
