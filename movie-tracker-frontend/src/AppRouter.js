import React from 'react';
import {BrowserRouter,Route,Redirect} from 'react-router-dom'
import LoginPage from './LoginPage'
import MainPage from './MainPage'
import AdminPanel from './Admin/AdminPanel'
import AddUser from './Admin/userOps/AddUser'

const AppRouter = () => (
    <BrowserRouter>
        <Route exact path="/login" component={LoginPage}/>
        <Route exact path="/" component={MainPage}/>
        <Route exact path="/admin-panel" component={AdminPanel}/>
        <Route exact path="/add-user" component={AddUser}/>
    </BrowserRouter>
)


export default AppRouter;