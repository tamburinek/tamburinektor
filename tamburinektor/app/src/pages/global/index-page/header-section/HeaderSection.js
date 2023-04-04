import {Link} from "react-router-dom";
import logo from "../../../../assets/png/logo.png";

import styles from './HeaderSection.module.scss'

export const HeaderSection = () => {
    return (
        <nav className={styles.navMenu}>
            <div className={styles.leftSide}>
                <img className={styles.logo} src={logo} alt={'logo'}/>
            </div>

            <div className={styles.rightSide}>
            <Link to={'/'} className={styles.link}>Profil</Link>
            <Link to={'/'} className={styles.link}>Materiály</Link>
            <Link to={'/'} className={styles.link}>Info</Link>
            <Link to={'/login'}>
                <button type={"button"} className={styles.button}>
                    Přihlásit
                </button>
            </Link>
            </div>
        </nav>
    )
}