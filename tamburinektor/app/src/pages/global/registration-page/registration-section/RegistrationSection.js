import styles from '../../login-page/login-section/LoginSection.module.scss'
import style from './RegistrationSection.module.scss'
import {Link} from "react-router-dom";

export const RegistrationSection = () => {

    return (
        <div className={styles.main}>
            <form className={styles.form} autoComplete="off">
                <h1 className={style.text}> Registrace </h1>
                <div className={styles.username}>
                    <label className={styles.label} htmlFor="username"> Uživatelské jméno </label>
                    <input
                        autoComplete="off"
                        id="username"
                        type={'text'}
                        placeholder={'@Tamburinek'}
                        className={styles.input}/>
                </div>
                <div className={style.names}>
                    <div className={style.name}>
                        <label className={styles.label} htmlFor="name"> Jméno </label>
                        <input
                            autoComplete="off"
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
                            className={style.input}/>
                    </div>
                </div>

                <div className={styles.password}>
                <label className={styles.label} htmlFor="password"> Heslo </label>
                <input
                    autoComplete="off"
                    id="password"
                    type={'password'}
                    className={styles.input}/>
                </div>

                <div className={styles.password}>
                <label className={styles.label} htmlFor="passwordAgain"> Heslo znovu </label>
                <input
                    autoComplete="off"
                    id="passwordAgain"
                    type={'password'}
                    className={styles.input}/>
                </div>

                <label className={styles.label} htmlFor="type"> Typ uživatele </label>
                <select className={style.selection} id="type" name="type" default="student">
                    <option value="student">Student</option>
                    <option value="teacher">Učitel</option>
                </select>

                <button className={styles.login}>Registrovat</button>

                <Link to={'/login'} className={styles.link}>Už máš účet?</Link>
            </form>
        </div>
    )
}