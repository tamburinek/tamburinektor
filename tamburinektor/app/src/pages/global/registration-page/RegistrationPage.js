import computer from "../../../assets/png/computer.png";
import logo from "../../../assets/png/logo.png";
import {RegistrationSection} from "./registration-section/RegistrationSection";
import styles from '../login-page/LoginPage.module.scss'
import {Link} from "react-router-dom";

export const RegistrationPage = () => {

    return (
        <div>
            <div className={styles.dot}/>
            <div className={styles.main}>
                <RegistrationSection/>
                <div className={styles.right}>
                    <img className={styles.computer} src={computer} alt={"computer"}/>
                </div>
            </div>
        </div>
    )
}