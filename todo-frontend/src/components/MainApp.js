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
      currentCategory: "All Categories",
      todos: [],
      message: null,
    };
    this.addTodo = this.addTodo.bind(this);
  }

  changeCategory = (category) => {
    this.setState({ currentCategory: category.categoryName });
  };
  addTodo() {
    this.props.history.push(`/add-todo`);
  }

  render() {
    let todoInfo = { title: "TODOs" };
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
            <Col xs="2">
              <CategoryComponent
                currentCategory={this.state.currentCategory}
                changeCategory={this.changeCategory}
                info={categoryInfo}
              />
            </Col>
            <Col xs="9">
                  <ToDoListComponent
                    currentCategory={this.state.currentCategory}
                    info={todoInfo}
                  />
              </Col>
                <Col xs="1">
                <div style={{marginLeft:-150, marginTop:-5}}>
                    <Button
                      color="success"
                      className="float-left"
                      onClick={this.addTodo}>
                      Add New
                    </Button>{" "}
                  </div>
                </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
