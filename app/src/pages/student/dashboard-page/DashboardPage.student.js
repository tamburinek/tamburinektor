import {SideSection} from "./side-bar/SideSection";
import styles from './DashboardPage.module.scss'
import {Link} from "react-router-dom";
import logo from "../../../assets/png/logo.png";
import {useEffect, useState} from "react";
import AuthService from "../../../services/auth.service";

export const DashboardPageStudent = () => {

    const [username, setUsername] = useState("");

    useEffect(() => {
        AuthService.getCurrentUser().then(response =>{
            console.log(response.data)
            localStorage.setItem("user", response.data.firstName + " " + response.data.lastName)
            setUsername(localStorage.getItem("user"))
        })
    },[]);

    return (
        <div>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <SideSection/>
            <span className={styles.name}>{username}</span>
        </div>
    )
}