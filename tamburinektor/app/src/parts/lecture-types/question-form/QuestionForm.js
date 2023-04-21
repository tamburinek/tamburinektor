import styles from "./QuestionForm.module.scss"
import {useEffect, useState} from "react";

export const QuestionForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <form className={styles.form}>
                <label>Popis otázky</label>
                <input type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}