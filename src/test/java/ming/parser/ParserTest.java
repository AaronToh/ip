package ming.parser;

import ming.command.Command;
import ming.command.TodoCommand;
import ming.exception.MingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseTodo_validInput_success() throws Exception {
        String input = "todo read book";
        Command command = Parser.parse(input);

        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parseMark_invalidIndex_throwsException() {
        String input = "mark 0";
        try {
            Parser.parse(input);
        } catch (MingException e) {
            assertEquals("Invalid task index: 0", e.getMessage());
        }
    }
}

