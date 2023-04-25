import styles from "./QuizForm.module.scss"
import {useEffect, useState} from "react";

export const QuizForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label className={styles.text}>Popis quizu</label>
                <input className={styles.name} type={"text"}/>
                <div className={styles.bottom}>
                    <div className={styles.create}>

                    </div>
                    <div className={styles.overview}>

                    </div>
                </div>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}