import styles from './LoginSection.module.scss'
import {Link} from "react-router-dom";

export const LoginSection = () => {

    return (
        <div className={styles.main}>
            <form className={styles.form} autoComplete="off">
                <h1 className={styles.text}> Přihlášení </h1>

                <div className={styles.username}>
                <label className={styles.label} htmlFor="username"> Uživatelské jméno </label>
                <input
                    autoComplete="off"
                    id="username"
                    type={'text'}
                    placeholder={'@Tamburinek'}
                    className={styles.input}/>
                </div>

                <div className={styles.password}>
                <label className={styles.label} htmlFor="password"> Heslo </label>
                <input
                    autoComplete="off"
                    id="password"
                    type={'password'}
                    className={styles.input}/>
                </div>

                <button className={styles.login}>Přihlásit</button>

                <Link to={'/register'} className={styles.link}>Ještě nemáš účet?</Link>
            </form>
        </div>
    )
}