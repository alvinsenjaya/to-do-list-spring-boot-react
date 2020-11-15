import React, { useEffect } from 'react';

function Signout({isAuthenticated, setIsAuthenticated}) {

  useEffect(() => {
		sessionStorage.removeItem('token');
		sessionStorage.removeItem('name');
		setIsAuthenticated(false);
  }, [])

	return (
		<div className="text-center">
			<h1>Successfully sign out</h1>
		</div>
	)
}

export default Signout;