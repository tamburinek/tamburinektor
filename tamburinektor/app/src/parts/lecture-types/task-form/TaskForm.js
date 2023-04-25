import styles from "./TaskForm.module.scss"
import {useEffect, useState} from "react";

export const TaskForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label>Otázka</label>
                <input className={styles.big} type={"text"}/>
                <label>Otázka - obrázek</label>
                <input className={styles.small} type={"text"}/>
                <label>Odpověď</label>
                <input className={styles.big} type={"text"}/>
                <label>Odpověď - obrázek</label>
                <input className={styles.small} type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}