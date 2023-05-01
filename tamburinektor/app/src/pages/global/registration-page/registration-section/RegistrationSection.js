import styles from '../../login-page/login-section/LoginSection.module.scss'
import style from './RegistrationSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {baseUrl} from "../../../../config/const";
import AuthService from "../../../../services/auth.service";
import authHeader from "../../../../services/auth-header";
import logo from "../../../../assets/png/logo.png";

export const RegistrationSection = () => {

const [name, setName] = useState('');
const [surname, setSurname] = useState('');
const [username, setUsername] = useState('');
const [password, setPassword] = useState('');
const [passwordAgain, setPasswordAgain] = useState('');

const options = ["ROLE_STUDENT", "ROLE_TEACHER"];
const [type, setType] = useState(options[0])

const [error, setError] = useState('');

const valid = (e) => {
        e.preventDefault();

        const userType = "ROLE_STUDENT";

        let valid = true;

        if (name.trim().length === 0 ||
            surname.trim().length === 0 ||
            username.trim().length === 0 ||
            password.trim().length === 0 ||
            passwordAgain.trim().length === 0
        ) {
            setError('Please fill data')
            valid = false;
            e.preventDefault()
            return;
        } else {
            const regex = /[^a-zA-ZÀ-Žà-ž]/;
            if (name.trim().length >= 2) {
                if (name.match(regex)) {
                    setError("Incorrect first name format.")
                    valid = false;
                    e.preventDefault();
                    return;

                }
            } else {
                setError("First name is too short")
                valid = false;
                e.preventDefault();
                return;
            }
            if (surname.trim().length >= 2) {
                if (surname.match(regex)) {
                    setError("Incorrect last name format.")
                    valid = false;
                    e.preventDefault();
                    return;
                }
            } else {
                setError("Last name is too short")
                valid = false;
                e.preventDefault();
                return;
            }
            if (username.trim().length >= 4) {
                if (surname.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}$/)) {
                    setError("Incorrect last name format.")
                    valid = false;
                    e.preventDefault();
                    return;
                }
            } else {
                setError("Username is too short")
                valid = false;
                e.preventDefault();
                return;
            }
            if (password.trim().length <= 3) {
                setError("Password is too short")
                valid = false;
                e.preventDefault();
                return;
            }
            if (passwordAgain !== password) {
                setError("Passwords do not match")
                valid = false;
                e.preventDefault();
                return;
            }
        }
        if (valid) {
            AuthService.register(name, surname, username, password, type)
            .then(
                () => {
                        window.location = "/login";
                    }
            );
        }
    }



    //return for app
    return (
        <div className={styles.main}>
            <Link to={'/'}> <img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <form className={styles.form} autoComplete="off" onSubmit={e => valid(e)}>
                <h1 className={style.text}> Registrace </h1>
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
                <div className={style.names}>
                    <div className={style.name}>
                        <label className={styles.label} htmlFor="name"> Jméno </label>
                        <input
                            autoComplete="off"
                            value={name}
                            onChange={(e) => {
                                setName(e.target.value)
                            }}
                            id="name"
                            type={'text'}
                            className={style.input}/>
                    </div>

                    <div className={style.surname}>
                        <label className={styles.label} htmlFor="surname"> Příjmení </label>
                        <input
                            autoComplete="off"
                            id="surname"
                            type={'text'}
                            value={surname}
                            onChange={(e) => {
                                setSurname(e.target.value)
                            }}
                            className={style.input}/>
                    </div>
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

                <div className={styles.password}>
                <label className={styles.label} htmlFor="passwordAgain"> Heslo znovu </label>
                <input
                    autoComplete="off"
                    id="passwordAgain"
                    type={'password'}
                    value={passwordAgain}
                    onChange={(e) => {
                        setPasswordAgain(e.target.value)
                    }}
                    className={styles.input}/>
                </div>

                <div className={style.selectionDiv}>
                    <label className={styles.label} htmlFor="typ"> Typ uživatele </label>
                    <select
                        value={type}
                        onChange={(e) => {
                            setType(e.target.value);
                        }}
                        className={style.selection} id="typ" name="type" default="student">
                        <option value="ROLE_STUDENT">Student</option>
                        <option value="ROLE_TEACHER">Učitel</option>
                    </select>
                </div>

                <button className={styles.login}>Registrovat</button>

                <Link to={'/login'} className={styles.link}>Už máš účet?</Link>
            </form>
        </div>
    )
}