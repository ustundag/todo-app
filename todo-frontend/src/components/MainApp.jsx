import React, { Component } from "react";
import Navi from "./Navi";
import ToDoList from "./ToDoList";
import CategoryList from "./CategoryList";
import { Container, Row, Col } from "reactstrap";

export default class MainApp extends Component {
  state ={currentCategory: ""}

  changeCategory = (category) => {
    this.setState({currentCategory: category.categoryName});
  }

  render() {
    let todoInfo  = { title: "To-Do List" };
    let categoryInfo = { title: "Categories"};
    return (
      <div className="MainApp">
      <Container>
          <Row>
            <Col xs="12"><Navi /></Col>
          </Row>
          <Row style={{margin:20}}></Row>
          <Row>
            <Col xs="3">
              <CategoryList currentCategory={this.state.currentCategory}
                            changeCategory={this.changeCategory}
                            info={categoryInfo} />
            </Col>
            <Col xs="9">
              <ToDoList  currentCategory={this.state.currentCategory}
                            info={todoInfo} />
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
