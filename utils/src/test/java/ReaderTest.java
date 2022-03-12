import org.junit.Test;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;


import java.util.LinkedList;
import java.util.Queue;

import static junit.framework.TestCase.assertNotNull;

public class ReaderTest {
    Queue<Thread> queue;
    Library library;

    @Test
    public void shouldCreateWriterObject() {
        queue = new LinkedList<>();
        library = new Library(queue);
        Reader reader = new Reader(library, "R1");
        assertNotNull("Reader object called.", reader);
    }
}
