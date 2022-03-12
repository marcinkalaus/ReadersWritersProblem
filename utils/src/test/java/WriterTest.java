import org.junit.Test;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.LinkedList;
import java.util.Queue;

import static junit.framework.TestCase.assertNotNull;

public class WriterTest {
    Queue<Thread> queue;
    Library library;

    @Test
    public void shouldCreateWriterObject() {
        queue = new LinkedList<>();
        library = new Library(queue);
        Writer writer = new Writer(library, "W1");
        assertNotNull("Writer object called.", writer);
    }

}
