import {SideSection} from "./side-bar/SideSection";
import styles from './DashboardPage.module.scss'
import {Link} from "react-router-dom";
import logo from "../../../assets/png/logo.png";

export const DashboardPageStudent = () => {

    return (
        <div>
            <Link to={'/'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <SideSection/>
            <span className={styles.name}>Arťom Ňorba</span>
        </div>
    )
}