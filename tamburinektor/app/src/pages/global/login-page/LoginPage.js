import computer from "../../../assets/png/computer.png";
import logo from "../../../assets/png/logo.png";
import {LoginSection} from "./login-section/LoginSection";
import styles from './LoginPage.module.scss'
import {Link} from "react-router-dom";

export const LoginPage = () => {

    return (
        <div>
            <div className={styles.dot}/>
            <div className={styles.main}>
                <LoginSection/>
                <div className={styles.right}>
                    <img className={styles.computer} src={computer} alt={"computer"}/>
                </div>
            </div>
        </div>
    )
}