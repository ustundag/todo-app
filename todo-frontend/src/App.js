import React, { Component, Router, Route, Switch } from "react";
import MainApp from './components/MainApp'

class App extends Component {
  render() {
    return (
      <div className="MainApp">
        {/*<Counter/>*/}
        <MainApp />
      </div>
    );
  }
}

export default App;