package pl.edu.agh.kis.pz1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.text.MessageFormat;

/**
 * Klasa definiujaca pisarza
 *
 * @author Marcin Kalaus
 */
public class Writer implements Runnable {

    Library library;
    SecureRandom random = new SecureRandom();
    String id;

    private static final Logger logger = LogManager.getLogger(Writer.class);

    /**
     * Konstruktor tworzacy obiekt pisarza
     *
     * @param library Zasob - czytelnia
     * @param id      Identyfikator pisarza
     */
    public Writer(Library library, String id) {
        this.library = library;
        this.id = id;
    }

    /**
     * Metoda definiujaca zadania dla watku pisarza oraz podajaca jego status na standardowe wyjscie.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                logger.info(MessageFormat.format("{0} {1} is waiting.", Thread.currentThread(), id));
                library.queue.add(Thread.currentThread());
                library.startWriting();

                logger.info(MessageFormat.format("{0} {1} starts writing. Number of writers: {2}, Number of readers: {3}",
                        Thread.currentThread(), id, library.getNoOfWritersInLibrary(), library.getNoOfReadersInLibrary()));

                Thread.sleep(random.nextInt(50) * 100L);

                library.stopWriting();
                logger.info(MessageFormat.format("{0} {1} stopped writing.", Thread.currentThread(), id));
            }
        } catch (InterruptedException e) {
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }
}
