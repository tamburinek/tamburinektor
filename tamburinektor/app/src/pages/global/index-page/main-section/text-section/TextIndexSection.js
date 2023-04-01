import {Link} from "react-router-dom";

import styles from './TextIndexSection.module.scss'

export const TextIndexSection = () => {
    return (
        <div className={styles.mainCont}>
            <div className={styles.texts}>
                <h1 className={styles.h1}>Už nikdy se netrap s učením a ukaž co je v tobě</h1>
                <h2 className={styles.h2}>S přehledem o dosavadním studiu ti už nikdy nic neunikne a ještě to natřeš svým spolužákům</h2>
            </div>
            <div className={styles.buttons}>
                <Link to={'/login'}>
                    <button type={"button"} className={styles.login}>
                        Log in
                    </button>
                </Link>
                <Link to={'/register'}>
                    <button type={"button"} className={styles.register}>
                        Register
                    </button>
                </Link>
            </div>
        </div>
    )
}