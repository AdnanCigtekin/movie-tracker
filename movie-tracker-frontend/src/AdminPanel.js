import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';

class AdminPanel extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            token : "",
            username : "",
            failed : false
        }
    }

    componentDidMount(){
        
        const cookies = new Cookies();
        var jwtCookie = cookies.get('jwt');
        
        if(jwtCookie == undefined){
            this.setState({
                failed : true
            })
            return;
        }
        var usrCookie = cookies.get('user');

    console.log(jwtCookie)
    console.log(usrCookie)
        this.setState({
            username : usrCookie
        }, () => {
            var myLink = 'http://localhost:8080/user/role/?name='+usrCookie
            console.log("Link : " +myLink)
            fetch(myLink,{
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + jwtCookie
                }
            })
            .then(response => response.text()
               )
            .then(response => {
                console.log("1");
               // console.log(response);
                var role = response;
               // console.log(role);
                if(role != "ADMIN" ){
                    this.setState({"failed" : true});
                    return;
                }
            })
            .catch(error => {
                console.error(error);
                this.setState({employees : "",token : ""});
            })
        })




    }

    render(){
        if(this.state.failed){
            return <div align="center">
            <h2>NOT LOGGED IN RETURNING TO MAIN PAGE...</h2>
             (<Redirect to="/" />);
        </div>
        }
        else{
           return  <h2>ADMIN PANEL</h2>
        }
    }

}


export default AdminPanel;