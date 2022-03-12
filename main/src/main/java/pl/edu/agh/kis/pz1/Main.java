package pl.edu.agh.kis.pz1;


import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Klasa Main
 *
 * @author Marcin Kalaus
 */
public class Main {
    /**
     * Glowna metoda programu
     *
     * @param args brak
     */
    public static void main(String[] args) {

        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);

        int noOfWriters = 3;
        int noOfReaders = 10;

        ArrayList<Thread> writers = new ArrayList<>();

        for (int i = 0; i < noOfWriters; i++) {
            writers.add(new Thread(new Writer(library, "W" + i)));
        }

        ArrayList<Thread> readers = new ArrayList<>();

        for (int i = 0; i < noOfReaders; i++) {
            readers.add(new Thread(new Reader(library, "R" + i)));
        }

        for (Thread thread : writers) {
            thread.start();
        }
        for (Thread thread : readers) {
            thread.start();
        }
    }
}
