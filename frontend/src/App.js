import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Todos from './components/ViewTodos'
import Header from './components/layout/Header';
import About from './components/pages/About';
import AddTodo from './components/AddTodo';
import UpdateTodo from './components/UpdateTodo';

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
