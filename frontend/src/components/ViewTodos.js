import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import moment from 'moment';

function Todos({id, setId}) {
	const [todos, setTodos] = useState([]);
	const [changed, setChanged] = useState(false);

	useEffect(() => {
		const loadData = async () => {
			const response = await axios.get('http://localhost:3001/api/todo');
			setTodos(response.data);
		}

		loadData();
	}, [changed])

	const markCompleted = async (id) => {
		await axios.put(`http://localhost:3001/api/todo/${id}/markcomplete`);
		setChanged(!changed);
	}

	const deleteTodo = async (id) => {
		await axios.delete(`http://localhost:3001/api/todo/${id}`);
		setChanged(!changed);
	}

	return (
		<div className="container">
			<h1 className="text-center">Todo List</h1>
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
            return <tr className={todo.isCompleted? 'completed' : ''} key={todo._id}>
              <td>{todo.title}</td>
              <td>{moment(todo.targetDate).format('ll')}</td>
              <td>{todo.isCompleted.toString()}</td>
							<td><button className="btn btn-success" onClick={() => markCompleted(todo._id)}>Mark Completed</button></td>
							<td><Link to={{pathname: `/update/${todo._id}`}}><button className="btn btn-primary">Update</button></Link></td>
							<td><button className="btn btn-danger" onClick={() => deleteTodo(todo._id)}>Delete</button></td>
            </tr>
          })
        }
        </tbody>
      </table>
    </div>
	);
}

export default Todos;