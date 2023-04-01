import styles from './LoginSection.module.scss'
import {Link} from "react-router-dom";

export const LoginSection = () => {

    return (
        <div className={styles.main}>
            <form className={styles.form} autocomplete="off">
                <h1 className={styles.text}> Login </h1>

                <div className={styles.username}>
                <label htmlFor="username"> Username </label>
                <input
                    id="username"
                    type={'text'}
                    placeholder={'@Tamburinek'}
                    className={styles.input}/>
                </div>

                <div className={styles.password}>
                <label htmlFor="password"> Password </label>
                <input
                    id="password"
                    type={'password'}
                    className={styles.input}/>
                </div>

                <button className={styles.login}>Login</button>

                <Link to={'/register'} >Don't have account yet?</Link>
            </form>
        </div>
    )
}