import styles from "./TaskForm.module.scss"
import {useEffect, useState} from "react";

export const TaskForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <form className={styles.form}>
                <label>Popis úkolu</label>
                <input type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}