package ming.storage;

import ming.exception.MingException;
import ming.model.TaskList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    void load_whenFileMissing_returnsEmptyList() throws MingException {
        Path file = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(file.toString());
        assertTrue(storage.load().isEmpty());
    }

    @Test
    void saveThenLoad_roundTrip_ok() throws MingException {
        Path file = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(file.toString());
        TaskList tasks = new TaskList();

        tasks.addTodo("buy milk");
        storage.save(tasks.getTasks());
        var loaded = storage.load();

        assertEquals(1, loaded.size());
        assertEquals("[T][ ] buy milk", loaded.get(0).toString());
    }
}
