import React from 'react'
import Cookies from 'universal-cookie';
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';


class AddUser extends React.Component {


    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: "",
            isEnabled: true,
            userRole: 0,
            messages: [],
            token: "",
            success: false,
            adding: false
        }
        this.handleCheckBoxChange = this.handleCheckBoxChange.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSelectBoxChange = this.handleSelectBoxChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    componentDidMount() {
        const cookies = new Cookies();
        this.setState({
            token: cookies.get("jwt")
        })
    }

    handleInputChange(event) {



        this.setState({
            [event.target.name]: event.target.value
        })
    }

    handleCheckBoxChange(event) {
        this.setState({
            [event.target.name]: !event.target.value
        });
        //console.log("ckbx : " + event.target.name + " " + event.target.value);
    }

    handleSelectBoxChange(event) {
        this.setState({
            userRole: event.target.value
        }, () => {
            if (this.state.userRole == "Seçiniz") {
                this.setState({
                    userRole: ""
                }, () => {
                    console.log(this.state.userRole)
                })

                this.setState({
                    messages: this.state.messages.concat('Şehir seçiniz')
                });
            }
            if (this.state.userRole != "Seçiniz") {
                console.log(this.state.userRole)
                this.setState({
                    //messages: this.state.messages.reduce('Passwords must match')
                    messages: this.state.messages.filter(item => item != "Şehir seçiniz")
                })
            }
        });
    }


    handleSubmit(event) {
        var errors = []

        this.setState({
            adding: true
        })

        if (this.state.username == "") {
            var error = `username field cannot be empty`
            errors.push(error);
        }
        if (this.state.password == "") {
            var error = `password field cannot be empty`
            errors.push(error);
        }
        console.log(this.state.userRole)
        if (this.state.userRole == 0) {
            var error = `please choose user role`
            errors.push(error);
        }

        this.setState({
            messages: errors
        }, () => {

            if (this.state.messages.length != 0) {
                console.log("Entered")
                this.setState({
                    adding: false
                })
                return;
            }

            fetch("http://localhost:8080/user/", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.state.token
                },
                body: JSON.stringify({
                    username: this.state.username,
                    password: this.state.password,
                    roleId: this.state.userRole,
                    enabled: this.state.isEnabled
                })
            })
                .then(response => response.text()
                )
                .then(response => {
                    console.log("response : " + response)
                    if (response == "OK") {
                        this.setState({
                            messages: ["User succesfully created!"],
                            success: true,
                            adding: false
                        })
                    }
                })
                .catch(error => {
                    console.error(error);
                    this.setState({ adding: false });
                })
        })
    }

    render() {



        var x = <div align="center">

            <h3>Add USER</h3>
            <table >
                <tbody align="center">

                    <tr>
                        <td>
                            Username:
            <input type="text" class="form-control" value={this.state.username} name="username" onClick={this.handleInputChange} onChange={this.handleInputChange} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password:
            <input type="password" class="form-control" value={this.state.password} name="password" onChange={this.handleInputChange} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" value={this.state.isEnabled} onChange={this.handleCheckBoxChange} name="isEnabled" /> enabled <br />
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <select class="form-control" defaultValue={this.state.userRole}
                                onChange={this.handleSelectBoxChange}
                            >
                                <option value="0">Seçiniz</option>
                                <option value="1">ADMIN</option>
                                <option value="2">USER</option>
                            </select> <br />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-dark btn-lg" onClick={this.handleSubmit}>Submit</button>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            {this.state.messages.map((item) => <li key={item}>{item}</li>)}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div >

        if (this.state.adding)
            return <div align="center">{x}
                <div  class="spinner-border" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>

        if (this.state.success)
            return <div> <div align="center">{x}</div>  <Link class="btn btn-dark btn-lg" align="center" to="/admin-panel">Go back to admin panel</Link></div>

        return <div><div align="center">{x}</div></div>;
    }
}



export default AddUser