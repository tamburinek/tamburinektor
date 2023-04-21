import styles from "./ImageForm.module.scss"
import {useEffect, useState} from "react";

export const ImageForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <form className={styles.form}>
                <label>Popis obrázku</label>
                <input type={"text"}/>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}