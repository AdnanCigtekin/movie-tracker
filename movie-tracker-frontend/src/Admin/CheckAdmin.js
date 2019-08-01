import React from 'react'
import Cookies from 'universal-cookie';
import { Redirect } from 'react-router';


class CheckAdmin extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            failed: false,
            token: ""
        }
        

    }

    componentDidMount() {
        const cookies = new Cookies();
       // var myTok = this.props.myToken
        this.setState({
            token: cookies.get("jwt")
        }, () => {
          //  console.log("tokennn" + this.state.token)


            // var jwtCookie = cookies.get('jwt');
            console.log("passed token" + this.state.token)
            if (this.state.token == undefined) {
                this.setState({
                    failed: true
                })
                return;
            }
            var usrCookie = cookies.get('user');

            // console.log(jwtCookie)


            var myLink = 'http://localhost:8080/user/role/?name=' + usrCookie
            console.log("Link : " + myLink)
            fetch(myLink, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.state.token
                }
            })
                .then(response => response.text()
                )
                .then(response => {
                    console.log("1");
                    // console.log(response);
                    var role = response;
                    // console.log(role);
                    if (role != "ADMIN") {
                        this.setState({ "failed": true });
                        return;
                    }
                })
                .catch(error => {
                    console.error(error);
                    this.setState({ failed: true, token: "" });
                })
        })

    }


    render() {
        if (this.state.failed) {
            return <div align="center">
                <h2>NOT LOGGED IN RETURNING TO MAIN PAGE...</h2>
                (<Redirect to="/" />);
            </div>

        }
        else {
            return <div></div>
        }


    }
}

export default CheckAdmin