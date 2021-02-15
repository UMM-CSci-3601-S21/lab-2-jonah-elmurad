package umm3601.todo;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

/**
 * Controller that manages requests for info about users.
 */
public class TodoController {

  private TodoDatabase database;

  /**
   * Construct a controller for todos.
   * <p>
   * This loads the "database" of todo info from a JSON file and stores that
   * internally so that (subsets of) todos can be returned in response to
   * requests.
   *
   * @param database the `Database` containing todo data
   */
  public TodoController(TodoDatabase database) {
    this.database = database;
  }

  /** Not added yet
  /**
   * Get the single user specified by the `id` parameter in the request.
   *
   * @param ctx a Javalin HTTP context
   *
  public void getUser(Context ctx) {
    String id = ctx.pathParam("id", String.class).get();
    User user = database.getUser(id);
    if (user != null) {
      ctx.json(user);
      ctx.status(201);
    } else {
      throw new NotFoundResponse("No user with id " + id + " was found.");
    }
  }
  */

  /**
   * Get a JSON response with a list of all the users in the "database".
   *
   * @param ctx a Javalin HTTP context
   */
  public void getTodos(Context ctx) {
    Todo[] todos = database.listTodos(ctx.queryParamMap());
    ctx.json(todos);
  }

}
