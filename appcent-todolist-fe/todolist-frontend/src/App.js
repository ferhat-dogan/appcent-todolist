import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import './App.css';
import Tasks from './components/Tasks';
import Todolists from './components/Todolists';

class App extends Component {
  constructor(props){
    super(props);
    this.state = { message: "" };
  }

  callbackFunction = (childData) => {
    this.setState({message:childData});
  }

  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <header className="App-header">
            <h1 className="App-title">To-do List App</h1>
          </header>
          <Switch>
            <Route exact path="/"><Todolists parentCallback = {this.callbackFunction} /></Route>
            <Route path="/tasksOfList"><Tasks tasksLink={this.state.message} /></Route>
          </Switch>
        </div>
      </BrowserRouter>
    );
  }
}
export default App;