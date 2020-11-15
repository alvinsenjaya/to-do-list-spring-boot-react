import React, { useEffect, useState } from 'react';

export default function Landing({isAuthenticated, setIsAuthenticated}) {
  const [message, setMessage] = useState('')

  useEffect(() => {
    if(isAuthenticated){
      setMessage(`Welcome, ${sessionStorage.getItem('name')}`);
    } else {
      setMessage('Please sign in to continue');
    }
  }, [isAuthenticated])

	return (
		<div className="text-center">
			<h1>Todo List Application</h1>
			{message}
		</div>
	)
}