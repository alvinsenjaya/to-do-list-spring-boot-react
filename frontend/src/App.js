import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Header from './components/header/Header';
import About from './components/about/About';
import Todos from './components/todo/ViewTodos'
import AddTodo from './components/todo/AddTodo';
import UpdateTodo from './components/todo/UpdateTodo';

import './bootstrap.min.css';
import './App.css';

function App () {
  return (
    <Router>
    <div className="App">
      <Header />
      <div>
        <Route exact path="/" component={Todos} />
        <Route exact path="/add" component={AddTodo} />
        <Route exact path="/update/:id" component={UpdateTodo} />
        <Route exact path="/about" component={About} />
      </div>
    </div>
    </Router>
  );
}

export default App;
