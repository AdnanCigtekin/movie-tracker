import React from 'react'
import { Link } from 'react-router-dom'


class UserOperations extends React.Component {


    render() {
        return <div>
            <div>
                <table align="center">
                    <tbody align="center">
                        <tr>
                            <td>
                                <h3 class="font-weight-light">USER OPERATIONS</h3>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Link class="btn btn-dark btn-lg" to="/add-user">Add</Link>
                            </td>
                        </tr>
                    </tbody>
                </table>


            </div>
        </div>
    }
}


export default UserOperations