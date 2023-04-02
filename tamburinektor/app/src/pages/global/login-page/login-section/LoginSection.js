import styles from './LoginSection.module.scss'
import {Link} from "react-router-dom";
import {baseUrl} from "../../../../config/const";
import AuthService from "../../../../services/auth.service";
import authHeader from "../../../../services/auth-header";
import {useEffect, useState} from "react";

export const LoginSection = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [error, setError] = useState('');

    const login = (e) => {
        e.preventDefault();
        AuthService.login(username, password).then(
            console.log("i am logged")
        )
    }


    return (
        <div className={styles.main}>
            <form className={styles.form} autoComplete="off" onSubmit={(e) => login(e)}>
                <h1 className={styles.text}> Přihlášení </h1>

                <div className={styles.username}>
                <label className={styles.label} htmlFor="username"> Uživatelské jméno </label>
                <input
                    autoComplete="off"
                    id="username"
                    type={'text'}
                    placeholder={'@Tamburinek'}
                    value={username}
                    onChange={(e) => {
                        setUsername(e.target.value)
                    }}
                    className={styles.input}/>
                </div>

                <div className={styles.password}>
                <label className={styles.label} htmlFor="password"> Heslo </label>
                <input
                    autoComplete="off"
                    id="password"
                    type={'password'}
                    value={password}
                    onChange={(e) => {
                        setPassword(e.target.value)
                    }}
                    className={styles.input}/>
                </div>

                <button className={styles.login}>Přihlásit</button>

                <Link to={'/register'} className={styles.link}>Ještě nemáš účet?</Link>
            </form>
        </div>
    )
}