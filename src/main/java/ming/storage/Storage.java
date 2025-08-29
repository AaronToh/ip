package ming.storage;

import ming.exception.MingException;
import ming.model.Deadline;
import ming.model.Event;
import ming.model.Task;
import ming.model.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final Path path;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    public List<Task> load() throws MingException {
        List<Task> tasks = new ArrayList<>();

        try {
            Path directory = path.getParent();
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner dataScanner;
        try {
            dataScanner = new Scanner(path.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (dataScanner.hasNextLine()) {
            String[] parts = dataScanner.nextLine().split(" \\| ");
            boolean done = parts[1].equals("1");
            switch (parts[0]) {
            case "T":
                Task todo = new Todo(parts[2]);
                if (done) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "D":
                Task deadline = new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
                if (done) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Task event = new Event(parts[2], LocalDateTime.parse(parts[3], formatter), LocalDateTime.parse(parts[4], formatter));
                if (done) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                System.out.println("Unknown task type: " + parts[0]);
            }
        }

        return tasks;
    }

    public void save(List<Task> tasks) throws MingException {
        try {
            FileWriter writer = new FileWriter(path.toFile());
            for (Task task : tasks) {
                writer.write(task.toDataString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new MingException("Error saving tasks: " + e.getMessage());
        }
    }
}
