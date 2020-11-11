import React, { useState, useEffect } from 'react';
import axios from 'axios';

import AddTodo from './AddTodo';

function Todos() {
	const [todos, setTodos] = useState([]);
	const [todoChange, setTodoChange] = useState(false);

	useEffect(() => {
		loadData();
	}, [todoChange])

	const loadData = async () => {
		const response = await axios.get('http://localhost:3001/api/todo');
		setTodos(response.data);
	}

	const getStyle = (todo) => {
		return {
			background: '#f4f4f4',
			padding: '5px',
			borderBottom: '1px dotted',
			textDecoration: todo.isCompleted? 'line-through' : 'none'
		}
	}

	const markComplete = async (id) => {
		await axios.put(`http://localhost:3001/api/todo/${id}`);
		setTodoChange(!todoChange);
	}

	const delTodo = async (id) => {
		await axios.delete(`http://localhost:3001/api/todo/${id}`);
		setTodoChange(!todoChange);
	}

	return (
		<div>
			<AddTodo todoChange={todoChange} setTodoChange={setTodoChange} />
			{
				todos.map((todo) => (
					<div key={todo._id} style={getStyle(todo)}>
						<p>
							{todo.title}
							<button style={btnStyle} onClick={() => delTodo(todo._id)}>Delete</button>
							<button style={btnCompleteStyle} onClick={() => markComplete(todo._id)}>Mark Completed</button>
						</p>
					</div>
				)) 
			}
		</div>
	);
}

const btnStyle = {
	background: '#ff0000',
	color: '#fff',
	border: 'none',
	padding: '5px 5px',
	borderRadius: '10%',
	cursor: 'pointer',
	float: 'right'
}

const btnCompleteStyle = {
	background: '#00ff55',
	color: '#000',
	border: 'none',
	padding: '5px 5px',
	borderRadius: '10%',
	cursor: 'pointer',
	float: 'right'
}

export default Todos;