import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Todos from './components/ViewTodos'
import Header from './components/layout/Header';
import About from './components/pages/About';
import './App.css';

function App () {
  return (
    <Router>
    <div className="App">
      <div className="container">
        <Header />
        <Route exact path="/" render={props => (
          <React.Fragment>
            <Todos />
          </React.Fragment>
        )} />
        <Route path="/about" component={About} />
      </div>
    </div>
    </Router>
  );
}

export default App;