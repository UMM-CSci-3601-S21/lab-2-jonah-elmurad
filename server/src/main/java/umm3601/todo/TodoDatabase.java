package umm3601.todo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import io.javalin.http.BadRequestResponse;

/**
 * A fake "database" of todo info
 */
public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public int size() {
    return allTodos.length;
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter status if defined
    if (queryParams.containsKey("status")) {
      String statusParam = queryParams.get("status").get(0);
      boolean targetStatus = false;

      if ("complete".equals(statusParam)) {
        targetStatus = true;
      }

      filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
    }

    // Filter with limit if defined
    if (queryParams.containsKey("limit")) {
      String limitParam = queryParams.get("limit").get(0);

      try {
        int targetLimit = Integer.parseInt(limitParam);
        if (targetLimit > filteredTodos.length || targetLimit < 0) {
        } else {
          Todo[] tempArray = new Todo[targetLimit];

          for (int i = 0; i < targetLimit; i++) {
            tempArray[i] = filteredTodos[i];
          }
          filteredTodos = tempArray;
        }
      } catch (NumberFormatException e) {
        throw new BadRequestResponse("Specified limit '" + limitParam + "' can't be parsed to an integer");
      }
    }

    // Filter with contains if defined
    if (queryParams.containsKey("contains")) {
      String bodyParam = queryParams.get("contains").get(0);
      filteredTodos = filterTodosByBody(filteredTodos, bodyParam);

     }

    // Filter with owner if defined
    if (queryParams.containsKey("owner")) {
      String ownerParam = queryParams.get("owner").get(0);
      filteredTodos = filterTodosByOwner(filteredTodos, ownerParam);
    }

    // Filter with category if defined
    if (queryParams.containsKey("category")) {
      String categoryParam = queryParams.get("category").get(0);
      filteredTodos = filterTodosByCategory(filteredTodos, categoryParam);
    }

    return filteredTodos;
  }

  /**
   * Get an array of all the todos having the target status.
   *
   * @param todos         the list of todos to filter by status
   * @param targetStatus the target status to look for
   * @return an array of all the todos from the given list that have the target
   *         status
   */
  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
	  return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }
  /** Get an array of all the todos having the target body.
   *
   * @param todos         the list of todos to filter by bodies
   * @param targetBody the target body to look for
   * @return an array of all todos from the given list that have the target
   *         body
   */
  public Todo[] filterTodosByBody(Todo[] todos, String targetBody) {
   return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }

  /**
   * Get an array of all the todos having the target owner.
   *
   * @param todos         the list of todos to filter by owner
   * @param targetOwner the target owner to look for
   * @return an array of all the todos from the given list that have the target
   *         owner
   */
  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
	  return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  /**
   * Get an array of all the todos having the target category.
   *
   * @param todos         the list of todos to filter by category
   * @param targetCategory the target category to look for
   * @return an array of all the todos from the given list that have the target
   *         category
   */
  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
	  return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

}
