import React, { useEffect, useState } from 'react';
import axios from 'axios';
import moment from 'moment';

function UpdateTodo({match, props}) {
	const [title, setTitle] = useState('');
  const [targetDate, setTargetDate] = useState('');
  const [message, setMessage] = useState('');

  const onSubmit = async (e) => {
    e.preventDefault();
		await axios.put(`http://localhost:3001/api/todo/${match.params.id}`, {title, targetDate});
    setMessage('Todo successfully updated');
  }

  useEffect(() => {
    const loadData = async () => {
      const response = await axios.get(`http://localhost:3001/api/todo/${match.params.id}`);
      setTitle(response.data.title);
      setTargetDate(moment(response.data.targetDate).format('YYYY-MM-DD'));
    }
    
		loadData();
  }, [match.params.id]);
  
  const showMessage = () => {
    if(message === ''){
      return <div></div>
    }
    return <div className="alert alert-success" role="alert">
      {message}
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
    </div>
	)
}

export default UpdateTodo;