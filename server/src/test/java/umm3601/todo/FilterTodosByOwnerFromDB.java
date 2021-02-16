package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.Database filterTodosByBody and listTodos with _body_ query
 * parameters
 */
public class FilterTodosByOwnerFromDB {

  @Test
  public void FilterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ownerBlancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals(43, ownerBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    Todo[] ownerSearchFromSmallLetterTodos = db.filterTodosByOwner(allTodos, "fry");
    assertEquals(0, ownerSearchFromSmallLetterTodos.length, "Incorrect number of todos with owner small letter");

    Todo[] ownerSantaTodos = db.filterTodosByOwner(allTodos, "Santa");
    assertEquals(0, ownerSantaTodos.length, "Incorrect number of todos with owner Santa");

  }



  @Test
  public void listTodosWithOwnerFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] { "Blanche" }));
    Todo[] bodyBlancheTodos = db.listTodos(queryParams);
    assertEquals(43, bodyBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    queryParams.put("owner", Arrays.asList(new String[] { "fry" }));
    Todo[] ownerSearchFromSmallLetterTodos = db.listTodos(queryParams);
    assertEquals(0, ownerSearchFromSmallLetterTodos.length, "Incorrect number of todos with owner small letter");

    queryParams.put("owner", Arrays.asList(new String[] { "Santa" }));
    Todo[] ownerSantaTodos = db.listTodos(queryParams);
    assertEquals(0, ownerSantaTodos.length, "Incorrect number of todos with owner Santa");

  }
}
