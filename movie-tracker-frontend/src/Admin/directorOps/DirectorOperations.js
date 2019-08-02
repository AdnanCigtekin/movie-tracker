import React from 'react'
import { Link } from 'react-router-dom'


class DirectorOperations extends React.Component {


    render() {
        return <div>
            <div>
                <table align="center">
                    <tbody align="center">
                        <tr>
                            <td>
                                <h3 class="font-weight-light">DIRECTOR OPERATIONS</h3>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Link class="btn btn-dark btn-lg" to="/add-director">Add</Link>
                            </td>
                        </tr>
                    </tbody>
                </table>


            </div>
        </div>
    }
}


export default DirectorOperations