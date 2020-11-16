import React, { useEffect, useState } from 'react';
import axios from 'axios';
import moment from 'moment';
import { useHistory } from "react-router-dom"; 

function UpdateTodo({isAuthenticated, setIsAuthenticated, match}) {
	const [title, setTitle] = useState('');
  const [targetDate, setTargetDate] = useState('');
  const [message, setMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  let history = useHistory();

  useEffect(() => {
		if(!isAuthenticated){
			history.push("/");
		}
	}, [isAuthenticated, history])

  function timeout(delay) {
    return new Promise( res => setTimeout(res, delay) );
  }

  const onSubmit = async (e) => {
    e.preventDefault();
  
    try {
      await axios.put(`http://localhost:3001/api/todo/${match.params.id}`, {title, targetDate}, {
        headers: {
          'Authorization': `Bearer ${sessionStorage.getItem('token')}`
        }
      });
    } catch(error){
      setMessage('');
      if (error.response) {
        setErrorMessage(error.response.data.message);
      } else {
        setErrorMessage('Error: something happened');
      }
      return;
    }

    setErrorMessage('');
    setMessage('Todo successfully updated');
    await timeout(1000);
    history.push("/todo");
  }

  useEffect(() => {
    const loadData = async () => {
      let response = null;
      try {
        response = await axios.get(`http://localhost:3001/api/todo/${match.params.id}`, {
          headers: {
						'Authorization': `Bearer ${sessionStorage.getItem('token')}`
					}
        });
      } catch(error){
        setMessage('');
        if (error.response) {
          setErrorMessage(error.response.data.message);
        } else {
          setErrorMessage('Error: something happened');
        }
        return;
      }
      setErrorMessage('');
      setTitle(response.data.title);
      setTargetDate(moment(response.data.targetDate).format('YYYY-MM-DD'));
    }
    
		loadData();
  }, [match.params.id]);

  useEffect(() => {
    setMessage('')
  }, [title, targetDate])
  
  const showMessage = () => {
    if(message === ''){
      return <div></div>
    }
    return <div className="alert alert-success" role="alert">
      {message}
    </div> 
  }

  const showErrorMessage = () => {
    if(errorMessage === ''){
      return <div></div>
    }

    return <div className="alert alert-danger" role="alert">
      {errorMessage}
    </div>
  }

	return (
		<div className="container">
      <form onSubmit={onSubmit}>
        <h1>Update Todo</h1>
        <div className="form-group">
          <label>Title</label>
          <input 
            value={title} 
            onChange={e => setTitle(e.target.value)} 
            className="form-control">
          </input>
        </div>
        <div className="form-group">
          <label>Target Date</label>
          <input 
            value={targetDate} 
            type="date" 
            onChange={e => setTargetDate(e.target.value)} 
            className="form-control">
          </input>
        </div>
        <button className="btn btn-primary">Update Todo</button>
      </form>
      {showMessage()}
      {showErrorMessage()}
    </div>
	)
}

export default UpdateTodo;