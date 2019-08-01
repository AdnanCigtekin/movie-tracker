import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';
import 'bootstrap/dist/css/bootstrap.min.css';

class LoginPage extends React.Component {


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



    handleSubmit(event) {
        console.log("user submitted name : " + this.state.username + " " + " password : " + this.state.password)
        const cookies = new Cookies();
        cookies.remove("jwt");
        cookies.remove("user");
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



                console.log("My token" + this.state.token)
            }

            ).catch(error => {
                console.error(error);

                this.setState({
                    username: "",
                    password: "",
                    isLoggingIn: false,
                    loggedIn : false
                });

            }).then(() => {
                fetch('http://localhost:8080/user/role/?name=' + this.state.username, {
                    method: "GET",
                    headers: {
                        'Authorization': 'Bearer ' + this.state.token
                    }
                }
                ).then(response => response.text()

                ).then(response => this.setState(
                    {
                        userRole: response

                    }

                )).then(() => {
                    console.log("USER ROLE : " + this.state.userRole)

                    if(this.state.userRole != "ADMIN" && this.state.userRole != "USER"){
                        this.setState({
                            isLoggingIn : false,
                            loggedIn : false
                        })
                        return
                    }

                    this.setState({
                        loggedIn: true,
                        isLoggingIn: false
                    })
                }).catch(error => {
                    console.error(error);

                    this.setState({
                        username: "",
                        password: "",
                        isLoggingIn: false,
                        loggedIn : false
                    });

                })

            })
    }



    render() {


        if (!this.state.loggedIn) {
            if (!this.state.isLoggingIn) {
                return <div >
                    <table align="center" class="jumbotron">
                        <tbody align="center">
                            <tr>
                                <td>
                                    <h2 class="display-4">LOGIN FORM</h2>
                                </td>
                            </tr>
                            <tr>

                                <td>
                                    Username:
            <input type="text" class="form-control" value={this.state.username} name="username" onChange={this.handleInput} />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Password:
            <input type="password" class="form-control" value={this.state.password} name="password" onChange={this.handleInput} />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-dark btn-lg" onClick={this.handleSubmit}>Login</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>




                </div>
            }
            else {
                return <div align="center">
                    <h2>LOGGING IN...</h2>
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            }
        }
        if (this.state.loggedIn) {
            return (<Redirect to="/" />);
        }
    }
}


export default LoginPage;

