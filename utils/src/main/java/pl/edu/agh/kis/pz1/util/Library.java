package pl.edu.agh.kis.pz1.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Queue;

/**
 * Klasa odzierciedlajaca zasob - czytelnia
 *
 * @author Marcin Kalaus
 */
public class Library {


    private int noOfWritersInLibrary = 0;
    private int noOfReadersInLibrary = 0;

    private static final Logger logger = LogManager.getLogger(Library.class);

    public Library(Queue<Thread> queue) {
        this.queue = queue;
    }

    Queue<Thread> queue;

    /**
     * Pobiera liczbe pisarzy w czytelni.
     *
     * @return liczba pisarzy w czytelni
     */
    public int getNoOfWritersInLibrary() {
        return noOfWritersInLibrary;
    }

    /**
     * Pobiera liczbe czytelnikow w czytelni.
     *
     * @return liczba czytelnikow w czytelni
     */
    public int getNoOfReadersInLibrary() {
        return noOfReadersInLibrary;
    }

    /**
     * Ustawia liczbe pisarzy w czytelni
     *
     * @param noOfWritersInLibrary liczba pisarzy w czytelni
     */
    public void setNoOfWritersInLibrary(int noOfWritersInLibrary) {
        this.noOfWritersInLibrary = noOfWritersInLibrary;
    }

    /**
     * Ustawia liczbe czytelnikow w czytelni
     *
     * @param noOfReadersInLibrary liczba czytelnikow w czytelni
     */
    public void setNoOfReadersInLibrary(int noOfReadersInLibrary) {
        this.noOfReadersInLibrary = noOfReadersInLibrary;
    }

    /**
     * Udostepnia dostep do zasobu dla pisania.
     */
    public synchronized void startWriting() {
        try {
            while (noOfWritersInLibrary > 0 || noOfReadersInLibrary > 0 || !Objects.equals(queue.peek(), Thread.currentThread())) {
                wait();
            }
            queue.remove();
            noOfWritersInLibrary += 1;
        } catch (InterruptedException e) {
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Aktualizuje liste pisarzy w czytelni oraz informuje pozostale watki o zmianie.
     */
    public synchronized void stopWriting() {
        noOfWritersInLibrary -= 1;
        notifyAll();
    }

    /**
     * Udostepnia dostep do zasobu dla czytania.
     */
    public synchronized void startReading() {
        try {
            while (noOfWritersInLibrary > 0 || noOfReadersInLibrary >= 5 || !Objects.equals(queue.peek(), Thread.currentThread()))
                wait();

            queue.remove();
            noOfReadersInLibrary += 1;
            notifyAll();
        } catch (InterruptedException e) {
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Aktualizuje liste czytajacych w czytelni oraz informuje pozostale watki o zmianie.
     */
    public synchronized void stopReading() {
        noOfReadersInLibrary -= 1;
        notifyAll();
    }
}
