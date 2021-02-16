package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.TodoDatabase filterTodosByStatus and listTodos with _status_ query
 * parameters
 */
public class FilterTodosByStatusFromDB {

  @Test
  public void filterTodosByStatus() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] statusCompleteTodos = db.filterTodosByStatus(allTodos, true);
    assertEquals(143, statusCompleteTodos.length, "Incorrect number of todos with status complete");

    Todo[] statusIncompleteTodos = db.filterTodosByStatus(allTodos, false);
    assertEquals(157, statusIncompleteTodos.length, "Incorrect number of todos with status incomplete");
  }

  @Test
  public void listTodosWithStatusFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("status", Arrays.asList(new String[] { "complete" }));
    Todo[] statusCompleteTodos = db.listTodos(queryParams);
    assertEquals(143, statusCompleteTodos.length, "Incorrect number of todos with status complete");

    queryParams.put("status", Arrays.asList(new String[] { "incomplete" }));
    Todo[] statusIncompleteTodos = db.listTodos(queryParams);
    assertEquals(157, statusIncompleteTodos.length, "Incorrect number of todos with status incomplete");
  }
}
