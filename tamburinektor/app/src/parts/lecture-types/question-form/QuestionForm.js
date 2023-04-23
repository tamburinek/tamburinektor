import styles from "./QuestionForm.module.scss"
import {useEffect, useState} from "react";

export const QuestionForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label className={styles.label}>Otázka</label>
                <input className={styles.question} type={"text"}/>
                <div className={styles.div}>
                    <label className={styles.label} htmlFor={"anonymous"}>Anonymní</label>
                    <input className={styles.check} type={"checkbox"} name={"anonymous"} id={"anonymous"}/>
                </div>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}