import React, { useState } from 'react';
import axios from 'axios';

function AddTodo({todoChange, setTodoChange}) {
	const [title, setTitle] = useState('');

	const onSubmit = async (e) => {
		e.preventDefault();
		await axios.post('http://localhost:3001/api/todo', {title});
		setTitle('');
		setTodoChange(!todoChange);
	}

	return (
		<form onSubmit={onSubmit} style={{display: 'flex'}}>
			<input type="text" value={title} onChange={(e) => setTitle(e.target.value)} name="title" placeholder="Add Todo..." style={{flex: '10', padding: '5px'}} />
			<input type="submit" value="Submit" className="btn" style={{flex: '1'}} />
		</form>
	)
}

export default AddTodo;