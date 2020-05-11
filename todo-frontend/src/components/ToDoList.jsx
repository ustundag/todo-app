import React, { Component } from "react";
import { Table, Button } from "reactstrap";

export default class ToDoList extends Component {
  constructor(props) {
    console.log("ToDoList constructor");
    super(props);
    this.state = {
      todos: [],
      message: null,
    };
    this.addTodoClicked = this.addTodoClicked.bind(this);
  }

  addTodoClicked() {
    this.props.history.push(`/todos/-1`);
  }

  render() {
    return (
      <div>
        <h3>
          {this.props.info.title} + {this.props.currentCategory}
        </h3>
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
              <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>
                  <Button color="danger">remove</Button>{" "}
                </td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>
                  <Button color="danger">remove</Button>{" "}
                </td>
              </tr>
            </tbody>
          </Table>
          <div className="row">
            <button className="btn btn-success" onClick={this.addTodoClicked}>
              Add
            </button>
          </div>
        </div>
      </div>
    );
  }
}
