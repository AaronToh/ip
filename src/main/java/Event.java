import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | "
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
