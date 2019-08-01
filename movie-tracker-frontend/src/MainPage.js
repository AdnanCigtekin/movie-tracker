import React from 'react'
import { Link } from 'react-router-dom'
import UpperBar from './UpperBar'
import 'bootstrap/dist/css/bootstrap.min.css';

class MainPage extends React.Component {
    //TODO : add upper bar to all pages
    render() {
        return (

            <div align="center">


                <UpperBar />


                <div class="jumbotron">
                
                    <h1 class="display-4">Movie Tracker</h1>
                    <hr class="my-4"></hr>


                    <table >

                        <tbody>
                            <tr>
                                <td>
                                    <Link class="btn btn-dark btn-lg" to="/login">Login</Link>
                                </td>
                                <td>
                                    <Link class="btn btn-dark btn-lg" to="/admin-panel">Admin Panel</Link>
                                </td>
                                {/* <td>
                                    <Link class="btn btn-dark btn-lg" to="/user-panel">User Panel</Link>
                                </td> */}

                            </tr>
                        </tbody>
                    </table>
                    {/* <a class="btn btn-dark btn-lg" href="/login" role="button">Login</a> */}

                </div>
{/* 
                <h1 >MOVIE TRACKER</h1>

                <table >

                    <tbody>
                        <tr>
                            <td>
                                <Link to="/login">login</Link>
                            </td>
                            <td>
                                <Link to="/admin-panel">Admin Panel</Link>
                            </td>
                            <td>
                                <Link to="/user-panel">User Panel</Link>
                            </td>

                        </tr>
                    </tbody>
                </table> */}



            </div>

        )
    }
}


export default MainPage;