import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';
import UserOperations from './userOps/UserOperations'
import MovieOperations from './movieOps/MovieOperations'
import DirectorOperations from './directorOps/DirectorOperations'
import CheckAdmin from './CheckAdmin'
import ListUsers from './userOps/ListUsers'
import ListMovies from './movieOps/ListMovies'
import ListDirectors from './directorOps/ListDirectors'
import UpperBar from './../UpperBar'
class AdminPanel extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            token: "",
            username: "",
            failed: false
        } 
    }
    componentDidMount() {
        const cookies = new Cookies();
        var jwtCookie = cookies.get("jwt")
        this.setState({
            token: jwtCookie
        }, () => {
            console.log("myToken : " + this.state.token);

        })
        //   token = cookies.get("jwt");
    }

    render() {

        return <div>
            <CheckAdmin />
            <UpperBar />
            <h2 className="display-4" align="center">ADMIN PANEL</h2>

            <table  align="center" >
                <tbody>
                    <tr>
                        <td class="jumbotron">
                            <UserOperations />
                            <ListUsers />
                        </td>
                        <td class="jumbotron align-top">
                            <MovieOperations />
                            <ListMovies />
                        </td>
                        <td class="jumbotron align-top">
                            <DirectorOperations />
                            <ListDirectors />
                        </td>
                    </tr>
                    
                </tbody>
            </table>

        </div>

    }

}


export default AdminPanel;