package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.TodoDatabase listTodos with _owner_ and _category_ query
 * parameters
 */
public class FilterTodosByCombinedFiltersFromDB {

  @Test
  public void listTodosWithCombinedFilters() throws IOException {
    TodoDatabase db = new TodoDatabase("/Todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));
    Todo[] ownerBlancheTodos = db.listTodos(queryParams);
    assertEquals(43, ownerBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    queryParams.clear();
    queryParams.put("category", Arrays.asList(new String[] { "homework" }));
    Todo[] categoryHomeworkTodos = db.listTodos(queryParams);
    assertEquals(79, categoryHomeworkTodos.length, "Incorrect number of todos with category homework");

    queryParams.clear();
    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));
    queryParams.put("category", Arrays.asList(new String[] { "homework" }));
    Todo[] categoryHomeworkOwnerBlancheTodos = db.listTodos(queryParams);
    assertEquals(14, categoryHomeworkOwnerBlancheTodos.length, "Incorrect number of todos with category homework and owner Blanche");
  }
}
