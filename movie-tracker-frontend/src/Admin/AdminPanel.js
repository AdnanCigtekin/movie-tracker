import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';
import UserOperations from './UserOperations'
import CheckAdmin from './CheckAdmin'
import ListUsers from './ListUsers'

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
            <h2 align="center">ADMIN PANEL</h2>

            <table>
                <tbody>
                    <tr>
                        <td>
                            <UserOperations />
                            <ListUsers />
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>

    }

}


export default AdminPanel;