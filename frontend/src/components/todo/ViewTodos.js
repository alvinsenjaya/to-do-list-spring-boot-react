import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import moment from 'moment';

function Todos({id, setId}) {
	const [todos, setTodos] = useState([]);
	const [changed, setChanged] = useState(false);
	const [errorMessage, setErrorMessage] = useState('');

	useEffect(() => {
		const loadData = async () => {
			let response = null;
			try {
				response = await axios.get('http://localhost:3001/api/todo', {
					headers: {
						'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
					}
				});
			} catch(error){
				if (error.response) {
					setErrorMessage(error.response.data.message);
				} else {
					setErrorMessage('Error: something happened');
				}
				return;
			}
			setErrorMessage('');
			setTodos(response.data);
		}

		loadData();
	}, [changed])

	const markCompleted = async (id) => {
		try {
      await axios.put(`http://localhost:3001/api/todo/${id}/markcomplete`, {}, {
				headers: {
					'Authorization': `Bearer ${sessionStorage.getItem('token')}`
				}
			});
    } catch(error){
      if (error.response) {
        setErrorMessage(error.response.data.message);
      } else {
        setErrorMessage('Error: something happened');
      }
      return;
		}
		setErrorMessage('');
		setChanged(!changed);
	}

	const deleteTodo = async (id) => {
		try {
      await axios.delete(`http://localhost:3001/api/todo/${id}`, {
				headers: {
					'Authorization': `Bearer ${sessionStorage.getItem('token')}`
				}
			});
    } catch(error){
      if (error.response) {
        setErrorMessage(error.response.data.message);
      } else {
        setErrorMessage('Error: something happened');
      }
      return;
		}
		setErrorMessage('');
		setChanged(!changed);
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
			<h1 className="text-center">Todo List</h1>
			{showErrorMessage()}
      <table className="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Target Date</th>
            <th>Is Completed?</th>
						<th>Mark Completed</th>
						<th>Update</th>
						<th>Delete</th>
          </tr>
        </thead>
        <tbody>
        {
          todos.map((todo) => {
            return <tr className={todo.isCompleted? 'completed' : ''} key={todo.id}>
              <td>{todo.title}</td>
              <td>{moment(todo.targetDate).format('ll')}</td>
              <td>{todo.isCompleted.toString()}</td>
							<td><button className="btn btn-success" onClick={() => markCompleted(todo.id)}>Mark Completed</button></td>
							<td><Link to={{pathname: `/update/${todo.id}`}}><button className="btn btn-primary">Update</button></Link></td>
							<td><button className="btn btn-danger" onClick={() => deleteTodo(todo.id)}>Delete</button></td>
            </tr>
          })
        }
        </tbody>
      </table>
    </div>
	);
}

export default Todos;