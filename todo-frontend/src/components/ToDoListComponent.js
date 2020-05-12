import React, { Component } from "react";
import { Table, Button } from "reactstrap";
import TodoDataService from '../TodoDataService.js'
import moment from "moment";

export default class ToDoListComponent extends Component {
  constructor(props) {
    console.log("ToDoList constructor");
    super(props);
    this.state = {
      todos: [],
      message: null,
    };
    this.deleteTodo      = this.deleteTodo.bind(this);
    this.refreshTodoList = this.refreshTodoList.bind(this);
  }

  componentWillUnmount() {
    console.log("componentWillUnmount");
  }
  shouldComponentUpdate(nextProps, nextState) {
    console.log("shouldComponentUpdate");
    console.log(nextProps);
    console.log(nextState);
    return true;
  }
  componentDidMount() {
    console.log("componentDidMount");
    this.refreshTodoList();
    console.log(this.state);
  }
  refreshTodoList() {
    TodoDataService.retrieveAllTodos().then((response) => {
      console.log('[ToDoListComponent] refreshTodoList');
      console.log(response);
      this.setState({ todos: response.data });
    });
  }
  deleteTodo(id) {
    TodoDataService.deleteTodo(id).then((response) => {
      this.setState({ message: 'Delete of todo ${id} Successful'});
      this.refreshTodoList();
    });
  }

  render() {
    return (
      <div>
        <h4>
          {this.props.info.title} in {this.props.currentCategory}
        </h4>
        <div className="container">
          <Table striped>
            <thead>
              <tr>
                <th>#</th>
                <th>Description</th>
                <th>Deadline</th>
                <th>Completed?</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {this.state.todos.map((todo) => (
                <tr key={todo.id}>
                  <th scope="row">{todo.id}</th>
                  <td>{todo.description}</td>
                  <td>{moment(todo.deadline).format("YYYY-MM-DD")}</td>
                  <td>{todo.completed.toString()}</td>
                  <td>
                    <Button className="btn btn-danger" 
                            onClick={() => this.deleteTodo(todo.id)}>
                            Remove
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}
