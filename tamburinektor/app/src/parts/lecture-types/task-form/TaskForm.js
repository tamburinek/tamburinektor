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
                <input type={"text"}/>
                <label>Otázka - obrázek</label>
                <input type={"text"}/>
                <label>Odpověď</label>
                <input type={"text"}/>
                <label>Odpověď - obrázek</label>
                <input type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}