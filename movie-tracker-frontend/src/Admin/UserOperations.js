import React from 'react'
import { Link } from 'react-router-dom'


class UserOperations extends React.Component{


    render(){
        return <div>
            <h3>USER OPERATIONS</h3>
            <Link to="/add-user">Add</Link>
        </div>
    }
}


export default UserOperations