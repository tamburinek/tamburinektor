import styles from "./QuizForm.module.scss"
import {useEffect, useState} from "react";

export const QuizForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <form className={styles.form}>
                <label>Popis quizu</label>
                <input type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}