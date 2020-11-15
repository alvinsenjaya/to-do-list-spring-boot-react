import React, { useEffect } from 'react';
import { useHistory } from "react-router-dom"; 

function Signout({isAuthenticated, setIsAuthenticated}) {
	let history = useHistory();

  useEffect(() => {
		sessionStorage.removeItem('token');
		sessionStorage.removeItem('name');
		setIsAuthenticated(false);
		history.push("/");
  }, [history, setIsAuthenticated])

	return (
		<div className="text-center">
			<h1>Successfully sign out</h1>
		</div>
	)
}

export default Signout;