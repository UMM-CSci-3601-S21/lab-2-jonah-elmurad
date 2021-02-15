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
  /**
   * Get the single todo specified by the given ID. Return `null` if there is no
   * user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user with that ID
   *
  public User getUser(String id) {
    return Arrays.stream(allUsers).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }
  */

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;

    /**
    // Filter age if defined
    if (queryParams.containsKey("age")) {
      String ageParam = queryParams.get("age").get(0);
      try {
        int targetAge = Integer.parseInt(ageParam);
        filteredUsers = filterUsersByAge(filteredUsers, targetAge);
      } catch (NumberFormatException e) {
        throw new BadRequestResponse("Specified age '" + ageParam + "' can't be parsed to an integer");
      }
    }
    // Filter company if defined
    if (queryParams.containsKey("company")) {
      String targetCompany = queryParams.get("company").get(0);
      filteredUsers = filterUsersByCompany(filteredUsers, targetCompany);
    }
    */

    // Filter status if defined
    if (queryParams.containsKey("status")) {
      String statusParam = queryParams.get("status").get(0);
      boolean targetStatus = false;

      if ("complete".equals(statusParam)) {
        targetStatus = true;
      }

      //boolean targetStatus = Boolean.parseBoolean(statusParam);
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

    return filteredTodos;
  }
  /**
  /**
   * Get an array of all the users having the target age.
   *
   * @param users     the list of users to filter by age
   * @param targetAge the target age to look for
   * @return an array of all the users from the given list that have the target
   *         age
   *
  public User[] filterUsersByAge(User[] users, int targetAge) {
    return Arrays.stream(users).filter(x -> x.age == targetAge).toArray(User[]::new);
  }

  /**
   * Get an array of all the users having the target company.
   *
   * @param users         the list of users to filter by company
   * @param targetCompany the target company to look for
   * @return an array of all the users from the given list that have the target
   *         company
   *
  public User[] filterUsersByCompany(User[] users, String targetCompany) {
    return Arrays.stream(users).filter(x -> x.company.equals(targetCompany)).toArray(User[]::new);
  }
  */

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
}
