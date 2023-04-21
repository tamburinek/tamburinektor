import styles from "./GraphForm.module.scss"
import {useEffect, useState} from "react";

export const GraphForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <form className={styles.form}>
                <label>Popis grafu</label>
                <input type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}