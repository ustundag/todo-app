import axios from "axios";

class TodoDataService {
  static API_URL = "http://localhost:8080/api/v1/todo/";

  retrieveAllTodos() {
    console.log("[TodoDataService] retrieveAllTodos: " + TodoDataService.API_URL);
    return axios.get(TodoDataService.API_URL);
  }

  deleteTodo(id) {
    console.log("[TodoDataService] deleteTodo: " + TodoDataService.API_URL + id);
    return axios.delete(TodoDataService.API_URL + id);
  }

  addTodo(todo) {
    console.log("[TodoDataService] retrieveAllTodos: " + TodoDataService.API_URL);
    return axios.post(TodoDataService.API_URL, todo);
  }
}

export default new TodoDataService();
