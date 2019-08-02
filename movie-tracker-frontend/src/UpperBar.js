import React from 'react';
import Cookies from 'universal-cookie';
import './index.css'
class UpperBar extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            username: ""
        }
    }

    componentDidMount() {
        const cookies = new Cookies();
        this.setState({
            username: cookies.get("user")
        })
    }

    render() {
        if (this.state.username == null) {
            return <div className="upper-bar">
                <nav id="navbar-example2" class="navbar navbar-dark bg-dark upper-bar">
                    <a class="navbar-brand" href="/user-panel"></a>

                </nav>
            </div>
        }
        return <div className="upper-bar">
            <nav id="navbar-example2" class="navbar navbar-dark bg-dark upper-bar">
                <a class="navbar-brand" href="/user-panel">WELCOME, {this.state.username}</a>
            </nav>
        </div>
    }
}


export default UpperBar