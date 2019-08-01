import React from 'react'
import { Link } from 'react-router-dom'
import UpperBar from './UpperBar'
class MainPage extends React.Component {
    //TODO : add upper bar to all pages
    render() {
        return (
            
            <div align="center">
                <UpperBar/>
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
                </table>



            </div>

        )
    }
}


export default MainPage;