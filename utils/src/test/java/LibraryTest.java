import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LibraryTest {
    Queue<Thread> queue;
    Library library;


    @Test
    public void no_Of_Readers_Should_Decrease() {
        queue = new LinkedList<>();
        library = new Library(queue);
        library.setNoOfReadersInLibrary(5);
        library.stopReading();

        Assertions.assertEquals(4, library.getNoOfReadersInLibrary());
    }

    @Test
    public void no_Of_Writers_Should_Decrease() {
        queue = new LinkedList<>();
        library = new Library(queue);
        library.setNoOfWritersInLibrary(1);
        library.stopWriting();

        Assertions.assertEquals(0, library.getNoOfWritersInLibrary());
    }

    @Test
    public void number_Of_Writers_In_Library_Should_be_1() throws InterruptedException {
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);

        int noOfWriters = 3;

        ArrayList<Thread> writers = new ArrayList<>();

        for (int i = 0; i < noOfWriters; i++) {
            writers.add(new Thread(new Writer(library, "W" + i)));
        }


        for (Thread thread : writers) {
            thread.start();
        }

        Thread.sleep(3000);
        Assertions.assertEquals(1, library.getNoOfWritersInLibrary());
        Assertions.assertNotNull(queue);

        for (Thread thread : writers) {
            thread.interrupt();
        }
    }

    @Test
    public void number_Of_Readers_In_Library_Should_be_5() throws InterruptedException {
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);


        int noOfReaders = 10;

        ArrayList<Thread> readers = new ArrayList<>();


        for (int i = 0; i < noOfReaders; i++) {
            readers.add(new Thread(new Reader(library, "R" + i)));
        }

        for (Thread thread : readers) {
            thread.start();
        }

        Thread.sleep(3000);
        Assertions.assertEquals(5, library.getNoOfReadersInLibrary());
        Assertions.assertNotNull(queue);

        for (Thread thread : readers) {
            thread.interrupt();
        }
    }
}



