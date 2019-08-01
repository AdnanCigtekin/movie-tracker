import React from 'react';
import Cookies from 'universal-cookie';

class UpperBar extends React.Component{

constructor(props){
    super(props)
    this.state = {
        username : ""
    }
}

componentDidMount(){
    const cookies = new Cookies();
    this.setState({
        username : cookies.get("user")
    })
}

    render(){
        return <div>
            <h1>WELCOME, {this.state.username}</h1>
        </div>
    }
}


export default UpperBar