import React from 'react'
import { Link } from 'react-router-dom'


class MovieOperations extends React.Component {


    render() {
        return <div>
            <div>
                <table align="center">
                    <tbody align="center">
                        <tr>
                            <td>
                                <h3 class="font-weight-light">MOVIE OPERATIONS</h3>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Link class="btn btn-dark btn-lg" to="/add-movie">Add</Link>
                            </td>
                        </tr>
                    </tbody>
                </table>


            </div>
        </div>
    }
}


export default MovieOperations