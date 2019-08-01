import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';


class LoginPage extends React.Component {
    _isMounted = false;

    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: "",
            token: "",
            userRole: "",
            loggedIn: false,
            isLoggingIn: false
        }
        this.handleInput = this.handleInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleInput(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    componentDidMount() {
        this._isMounted = true;
    }

    componentWillUnmount() {
        this._isMounted = false;
    }

    handleSubmit(event) {
        console.log("user submitted name : " + this.state.username + " " + " password : " + this.state.password)
        const cookies = new Cookies();
        this.setState({
            isLoggingIn: true
        })

        fetch('http://localhost:8080/login/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.state.token
            },
            body: JSON.stringify({
                username: this.state.username,
                password: this.state.password
            })
        }).then(res => res.json())
            .then(response => this.setState(
                {
                    token: response.token,

                }

            )
            ).then(() => {
                console.log("SUCCESS" + JSON.stringify(this.state.token))

                cookies.set('jwt', this.state.token, { path: '/' });
                cookies.set("user", this.state.username, { path: "/" });
                if (this._isMounted) {
                    this.setState({
                        loggedIn: true,
                        isLoggingIn: false
                    })
                }
            }

            ).catch(error => {
                console.error(error);
                if (this._isMounted) {
                    this.setState({
                        username: "",
                        password: "",
                        isLoggingIn: false
                    });
                }
            }).then(fetch('http://localhost:8080/user/role/?name=' + this.state.username, {
                method: "GET",
                headers: {
                    'Authorization': 'Bearer ' + cookies.get("jwt")
                }
            }).then(response => response.text()

            ).then(response => this.setState(
                {
                    userRole: response

                }

            )).then(() => {
                console.log("USER ROLE : " + this.state.userRole)
            })
            )
    }



    render() {


        if (!this.state.loggedIn) {
            if (!this.state.isLoggingIn) {
                return <div>
                    Username:
            <input type="text" value={this.state.username} name="username" onChange={this.handleInput} />
                    Password:
            <input type="text" value={this.state.password} name="password" onChange={this.handleInput} />
                    <button onClick={this.handleSubmit}>Login</button>

                </div>
            }
            else {
                return <div align="center">
                    <h2>LOGGING IN...</h2>

                </div>
            }
        }
        if (this.state.loggedIn) {
            return (<Redirect to="/" />);
        }
    }
}


export default LoginPage;

