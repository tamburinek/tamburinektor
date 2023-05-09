import {Link} from "react-router-dom";

import styles from './TextIndexSection.module.scss'

export const TextIndexSection = () => {
    return (
        <div className={styles.mainCont}>
            <div className={styles.texts}>
                <h1 className={styles.h1}>Už nikdy <span className={styles.red}> se netrap</span> s učením a ukaž co je v tobě</h1>
                <h2 className={styles.h2}>S přehledem o dosavadním studiu ti už nikdy <span className={styles.red}> nic neunikne</span>  a ještě to <span className={styles.red}> natřeš</span>  svým spolužákům</h2>
            </div>
            <div className={styles.buttons}>
                <Link to={'/login'}>
                    <button type={"button"} className={styles.login}>
                        Přihlásit
                    </button>
                </Link>
                <Link to={'/register'}>
                    <button type={"button"} className={styles.register}>
                        Registrovat
                    </button>
                </Link>
            </div>
        </div>
    )
}