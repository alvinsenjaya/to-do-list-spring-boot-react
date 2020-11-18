import axios from 'axios';
import React, { useEffect, useState } from 'react';

export default function Landing({isAuthenticated, setIsAuthenticated}) {
  const [message, setMessage] = useState('')
  const [numberAllTodoNotCompleted, setNumberAllTodoNotCompleted] = useState(0);
  const [numberAllTodo, setNumberAllTodo] = useState(0);
  const [errorMessage, setErrorMessage] = useState('');

  const showErrorMessage = () => {
    if(errorMessage === ''){
      return <div></div>
    }

    return <div className="alert alert-danger" role="alert">
      {errorMessage}
    </div>
  }

  useEffect(() => {
    async function getAndSetNumberAllTodo() {
      try{
        const response = await axios.get('http://localhost:3001/api/todo/count', {
          headers: {
            'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
          }
        });
        setNumberAllTodo(response.data.count);
      } catch (error) {
        setMessage('');
        if (error.response) {
          setErrorMessage(error.response.data.message);
        } else {
          setErrorMessage('Error: something happened');
        }
      }
    }

    async function getAndSetNumberAllTodoNotCompleted() {
      try{
        const response = await axios.get('http://localhost:3001/api/todo/count?isCompleted=false', {
          headers: {
            'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
          }
        });

        setNumberAllTodoNotCompleted(response.data.count);
      } catch (error) {
        setMessage('');
        if (error.response) {
          setErrorMessage(error.response.data.message);
        } else {
          setErrorMessage('Error: something happened');
        }
      }
      
    }
    if(isAuthenticated){
      getAndSetNumberAllTodo();
      getAndSetNumberAllTodoNotCompleted();
      setMessage(`Welcome, ${sessionStorage.getItem('name')}. You have ${numberAllTodoNotCompleted} todo not completed out of ${numberAllTodo} todo.`);
    } else {
      setMessage('Please sign in to continue');
    }
  }, [isAuthenticated, numberAllTodo, numberAllTodoNotCompleted])

	return (
		<div className="text-center">
			<h1>Todo List Application</h1>
      {showErrorMessage()}
			{message}
		</div>
	)
}