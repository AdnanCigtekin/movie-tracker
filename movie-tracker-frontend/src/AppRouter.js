import React from 'react';
import {BrowserRouter,Route,Redirect} from 'react-router-dom'
import LoginPage from './LoginPage'
import MainPage from './MainPage'
import AdminPanel from './AdminPanel'

const AppRouter = () => (
    <BrowserRouter>
        <Route exact path="/login" component={LoginPage}/>
        <Route exact path="/" component={MainPage}/>
        <Route exact path="/admin-panel" component={AdminPanel}/>
    </BrowserRouter>
)


export default AppRouter;