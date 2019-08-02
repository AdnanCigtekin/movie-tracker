import React from 'react';
import Cookies from 'universal-cookie';

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
            return <div className="flex-fill">
                <nav id="navbar-example2" class="navbar navbar-dark bg-dark flex-fill">
                    <a class="navbar-brand" href="/user-panel"></a>

                </nav>
            </div>
        }
        return <div className="d-flex">
            <nav id="navbar-example2" class="navbar navbar-dark bg-dark flex-fill">
                <a class="navbar-brand" href="/user-panel">WELCOME, {this.state.username}</a>
            </nav>
        </div>
    }
}


export default UpperBar