package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.TodoDatabase listTodosWithLimit and listTodos with _limit_ query
 * parameters
 */
public class LimitNumberOfTodos {
  @Test
  public void listTodosWithLimit() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("limit", Arrays.asList(new String[] { "27" }));
    Todo[] limit27Todos = db.listTodos(queryParams);
    assertEquals(27, limit27Todos.length, "Incorrect number of todos with limit 27");

    queryParams.put("limit", Arrays.asList(new String[] { "301" }));
    Todo[] limit301Todos = db.listTodos(queryParams);
    assertEquals(300, limit301Todos.length, "Incorrect number of todos with limit 301");

    queryParams.put("limit", Arrays.asList(new String[] { "0" }));
    Todo[] limit0Todos = db.listTodos(queryParams);
    assertEquals(0, limit0Todos.length, "Incorrect number of todos with limit 0");

    queryParams.put("limit", Arrays.asList(new String[] { "-5" }));
    Todo[] limitMinus5Todos = db.listTodos(queryParams);
    assertEquals(300, limitMinus5Todos.length, "Incorrect number of todos with limit -5");
  }
}
