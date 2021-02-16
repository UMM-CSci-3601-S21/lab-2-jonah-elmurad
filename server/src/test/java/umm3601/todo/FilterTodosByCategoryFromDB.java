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
public class FilterTodosByCategoryFromDB {

  @Test
  public void FilterTodosByCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] categoryHomeworkTodos = db.filterTodosByCategory(allTodos, "homework");
    assertEquals(79, categoryHomeworkTodos.length, "Incorrect number of todos with category homework");

    Todo[] categorySearchFromCapitalLetterTodos = db.filterTodosByCategory(allTodos, "Homework");
    assertEquals(0, categorySearchFromCapitalLetterTodos.length, "Incorrect number of todos with container Homework");

    Todo[] categorySantaTodos = db.filterTodosByCategory(allTodos, "Santa");
    assertEquals(0, categorySantaTodos.length, "Incorrect number of todos with category Santa");

  }



  @Test
  public void listTodosWithCategoryFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("category", Arrays.asList(new String[] { "homework" }));
    Todo[] categoryHomeworkTodos = db.listTodos(queryParams);
    assertEquals(79, categoryHomeworkTodos.length, "Incorrect number of todos with category homework");

    queryParams.put("category", Arrays.asList(new String[] { "Homework" }));
    Todo[] categorySearchFromCapitalLetterTodos = db.listTodos(queryParams);
    assertEquals(0, categorySearchFromCapitalLetterTodos.length, "Incorrect number of todos with category Homework");

    queryParams.put("category", Arrays.asList(new String[] { "Santa" }));
    Todo[] categorySantaTodos = db.listTodos(queryParams);
    assertEquals(0, categorySantaTodos.length, "Incorrect number of todos with category Santa");

  }
}
