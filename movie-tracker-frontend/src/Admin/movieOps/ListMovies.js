import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';

class ListMovies extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            movies: [],
            updating: false,
            deleting: false,
            selectedItemId: -1,
            selectedItemName: "",
            selectedItemDuration: "",
            selectedItemDirectors: "",
            selectedItemGenre: "",
            selectedItemName: "",
            selectedItemReleaseDate: "",
            selectedItemRoles: [],
            selectedItemRole: "",
            token: "",
            messages: []
        }
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleDoUpdate = this.handleDoUpdate.bind(this);
        this.handleInput = this.handleInput.bind(this);
        this.handleDelete = this.handleDelete.bind(this);

    }

    handleDelete(event){
        var errors = []


        console.log()

        var myId = event.target.name;
        console.log("Key : " + myId)

        fetch("http://localhost:8080/movie/", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.state.token
            },
            body: JSON.stringify({
                id : myId
            })
        }).then(response => response.json())
        .then( response => {
           console.log("deleted successfully")
           this.componentDidMount()
        }

        )

    }

    handleDoUpdate() {

        var errors = []

        if(this.state.selectedItemName == ""){
            errors.push("Username cant be empty");
        }


        if(this.state.selectedItemDuration == ""){
            errors.push("Password cant be empty");
        }
        if(errors.length != 0){
            this.setState({
                messages : errors
            })
            return;
        }
        const cookies = new Cookies();

        var token = cookies.get("jwt")
        fetch("http://localhost:8080/movie/", {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({
                username: this.state.selectedItemName,
                password: this.state.selectedItemDuration,
                roleId : this.state.selectedItemRole,
                id : this.state.selectedItemId
            })
        })
            .then(response => response.json()
            )
            .then(response => {
                var msgs = []
                msgs.push("Successfully updated")
                this.setState({
                    messages: msgs.messages,
                    updating : false
                }, () => {
                    this.componentDidMount()
                    console.log("users: " + this.state.movies)
                })
            })
            .catch(error => {
                console.error(error);
                this.setState({ failed: true, token: "" });
            })
    }

    handleInput(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    componentDidMount() {
        const cookies = new Cookies();

        var token = cookies.get("jwt")
        this.setState({
            token : token
        })
       // this.state.token = token;
        if (token == "") {
            console.log("Couldnt find token")
            return;
        }

        fetch("http://localhost:8080/movie/search-all/", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        })
            .then(response => response.json()
            )
            .then(response => {
                
                this.setState({
                    movies : response.datas
                }, () => {
                    console.log("users: " + this.state.movies)
                })
            })
            .catch(error => {
                console.error(error);
                this.setState({ failed: true, token: "" });
            })

    }

    handleUpdate(event) {

        console.log("updated item " + event.target.name);
        this.setState({
            updating: true,
            selectedItemName: event.target.name
        })




        var myLink = 'http://localhost:8080/user/search/?name=' + event.target.name;
        console.log("myLink : " + myLink)
        fetch(myLink, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.state.token
            }
        }).then(res => res.json())
            .then(response => this.setState(
                {
                    selectedItemId: response.id,
                    selectedItemDirectors: response.directors,
                    selectedItemDuration : response.duration,
                    selectedItemGenre : response.genre,
                    selectedItemReleaseDate : response.releaseDate
                }

            )).then(() => {
                console.log("id : " + this.state.selectedItemId + " role" + this.state.selectedItemRoles[0].name)
                this.setState({
                    selectedItemRole: this.state.selectedItemRoles[0].id
                })
            })
            .catch(error => {
                console.error(error);
                this.setState({ failed: true, token: "" });
            })


    }

    render() {

        var userList = <div class="jumbotron">
            <table >
                <tbody >
                    <tr>
                        <td>
                            ID
                        </td>

                        <td>
                            Username
                        </td>

                        <td>
                            Director
                        </td>

                        <td>
                            Release Date
                        </td>

                        <td>
                            Genre
                        </td>
                        <td>
                            Duration
                        </td>
                    </tr>
                    {this.state.movies.map((item) =>
                        <tr>
                            <td>
                                {item.id}
                            </td>
                            <td>
                                {item.name}
                            </td>
                            <td>
                                {item.directors.map((item) =>
                                    <div>
                                        <div >
                                            {item.name} 
                                        </div>
                                    </div>)}
                            </td>
                            <td>
                                {item.releaseDate}
                            </td>
                            <td>
                                {item.genre}
                            </td>
                            <td>
                                {item.duration}
                            </td>
                            <td>
                                <button class="btn btn-dark btn-lg" onClick={this.handleUpdate} name={item.username}>Update</button>
                            </td>
                            <td>
                                <button class="btn btn-dark btn-lg" onClick={this.handleDelete} name={item.id}>Delete</button>
                            </td>
                        </tr>)}
                        <tr>
                            <td>
                                {this.state.messages}
                            </td>
                        </tr>
                </tbody>
            </table>
        </div>
        if (this.state.updating) {

            return <div>
                {userList}
                <table class="jumbotron">
                    <tbody >
                        <tr>
                            <td>Username :</td>
                        </tr>
                        <tr>
                            {/* <td>{this.state.selectedItemUsername}</td> */}
                            <td>
                                <input type="text" value={this.state.selectedItemName} name="selectedItemUsername" onChange={this.handleInput} />
                            </td>
                        </tr>
                        <tr>
                            <td>ID :</td>
                        </tr>
                        <tr>
                            {/* <td>
                        <input type="text" value={this.state.selectedItemId} name="selectedItemId" onChange={this.handleInput} />
                        </td> */}
                            <td>{this.state.selectedItemId}</td>
                        </tr>
                        <tr>
                            <td>ROLE:</td>
                        </tr>
                        <tr>
                            <td>
                                {/* <td>{this.state.selectedItemRoles.map((item) => <div>
                                <input type="text" value={this.state.selectedItemRole} name="selectedItemRole" onChange={this.handleInput} />
                            </div>)} */}
                                
                                <select selected={this.state.selectedItemRole}
                                    onChange={this.handleSelectBoxChange}
                                >
                                    <option value="1"selected={this.state.selectedItemRole == "1"}>ADMIN</option>
                                    <option value="2" selected={this.state.selectedItemRole == "2"}>USER</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>PASSWORD:</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="password" value={this.state.selectedItemDuration} name="selectedItemPassword" onChange={this.handleInput} />

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button class="btn btn-dark btn-lg" onClick={this.handleDoUpdate}>Update</button>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
        }

        return userList
    }

}

export default ListMovies;