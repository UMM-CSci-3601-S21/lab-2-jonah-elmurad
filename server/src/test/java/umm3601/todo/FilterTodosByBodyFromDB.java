package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.TodoDatabase filterTodosByBody and listTodos with _body_ query
 * parameters
 */
public class FilterTodosByBodyFromDB {

  @Test
  public void FilterTodosByBody() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] bodyNostrudTodos = db.filterTodosByBody(allTodos, "Nostrud");
    assertEquals(14, bodyNostrudTodos.length, "Incorrect number of todos with body Nostrud");

    Todo[] bodyCompleteLineTodos = db.filterTodosByBody(allTodos, "Officia nisi nulla eiusmod fugiat ex nulla amet reprehenderit velit. Ullamco elit non aliquip consectetur");
    assertEquals(1, bodyCompleteLineTodos.length, "Incorrect number of todos with body Officia nisi nulla eiusmod fugiat ex nulla amet reprehenderit velit. Ullamco elit non aliquip consectetur");

    Todo[] bodySantaTodos = db.filterTodosByBody(allTodos, "Santa");
    assertEquals(0, bodySantaTodos.length, "Incorrect number of todos with body Santa");

  }



  @Test
  public void listTodosWithBodyFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("contains", Arrays.asList(new String[] { "Nostrud" }));
    Todo[] bodyNostrudTodos = db.listTodos(queryParams);
    assertEquals(14, bodyNostrudTodos.length, "Incorrect number of todos with body Nostrud");

    queryParams.put("contains", Arrays.asList(new String[] { "Officia nisi nulla eiusmod fugiat ex nulla amet reprehenderit velit. Ullamco elit non aliquip consectetur" }));
    Todo[] bodyCompleteLineTodos = db.listTodos(queryParams);
    assertEquals(1, bodyCompleteLineTodos.length, "Incorrect number of todos with body Officia nisi nulla eiusmod fugiat ex nulla amet reprehenderit velit. Ullamco elit non aliquip consectetur");

    queryParams.put("contains", Arrays.asList(new String[] { "Santa" }));
    Todo[] bodySantaTodos = db.listTodos(queryParams);
    assertEquals(0, bodySantaTodos.length, "Incorrect number of todos with body Santa");

  }
}
