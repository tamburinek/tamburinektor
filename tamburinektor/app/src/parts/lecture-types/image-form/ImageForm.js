import styles from "./ImageForm.module.scss"
import {useEffect, useState} from "react";

export const ImageForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label className={styles.label}>Popis obrázku</label>
                <input className={styles.description} type={"text"}/>
                <label className={styles.label}>Odkaz</label>
                <input className={styles.link} type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}