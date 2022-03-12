package pl.edu.agh.kis.pz1.util;

import java.security.SecureRandom;
import java.text.MessageFormat;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Klasa definiujaca czytelnika
 *
 * @author Marcin Kalaus
 */
public class Reader implements Runnable {

    Library library;
    String id;
    SecureRandom random = new SecureRandom();

    private static final Logger logger = LogManager.getLogger(Reader.class);

    /**
     * Konstruktor tworzacy obiekt czytelnika
     *
     * @param library Zasob - czytelnia
     * @param id      Identyfikator czytelnika
     */
    public Reader(Library library, String id) {
        this.library = library;
        this.id = id;
    }

    /**
     * Metoda definiujaca zadania dla watku czytelnika oraz podajaca jego status na standardowe wyjscie.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                logger.info(MessageFormat.format("{0} {1} is waiting.", Thread.currentThread(), id));
                library.queue.add(Thread.currentThread());
                library.startReading();

                logger.info(MessageFormat.format("{0} {1} starts reading. Number of writers: {2}, Number of readers: {3}",
                        Thread.currentThread(), id, library.getNoOfWritersInLibrary(), library.getNoOfReadersInLibrary()));

                Thread.sleep(random.nextInt(50) * 100L);

                library.stopReading();
                logger.info(MessageFormat.format("{0} {1} stopped reading.", Thread.currentThread(), id));
            }
        } catch (InterruptedException e) {
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }
}
