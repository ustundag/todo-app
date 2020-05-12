import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import MainApp from "./components/MainApp";
import AddTodoComponent from "./components/AddTodoComponent";

export default class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route exact path="/add-todo" component={AddTodoComponent} />
          <Route exact path="/" component={MainApp} />
        </Switch>
      </Router>
    );
  }
}
