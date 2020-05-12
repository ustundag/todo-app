import React, { Component } from "react";
import { Container, Row, Col, Button } from "reactstrap";
import NaviComponent from "./NaviComponent";
import ToDoListComponent from "./ToDoListComponent";
import CategoryComponent from "./CategoryComponent";

export default class MainApp extends Component {
  constructor(props) {
    console.log("constructor");
    super(props);
    this.state = {
      currentCategory: "",
      todos: [],
      message: null,
    };
    this.addTodoClicked = this.addTodoClicked.bind(this);
  }

  changeCategory = (category) => {
    this.setState({ currentCategory: category.categoryName });
  };
  addTodoClicked() {
    this.props.history.push(`/add-todo`);
  }

  render() {
    let todoInfo = { title: "To-Do List" };
    let categoryInfo = { title: "Categories" };
    return (
      <div className="MainApp">
        <Container>
          <Row>
            <Col xs="12">
              <NaviComponent />
            </Col>
          </Row>
          <Row style={{ margin: 20 }}></Row>
          <Row>
            <Col xs="3">
              <CategoryComponent
                currentCategory={this.state.currentCategory}
                changeCategory={this.changeCategory}
                info={categoryInfo}
              />
            </Col>
            <Col xs="9">
              <Row>
                <Col xs="12">
                  <ToDoListComponent
                    currentCategory={this.state.currentCategory}
                    info={todoInfo}
                  />
                </Col>
              </Row>
              <Row>
                <Col xs="12">
                  <div>
                    <Button
                      color="success"
                      className="float-left"
                      onClick={this.addTodoClicked}
                    >
                      Add New
                    </Button>{" "}
                  </div>
                </Col>
              </Row>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
