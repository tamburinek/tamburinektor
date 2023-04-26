import styles from "./TaskForm.module.scss"
import {useEffect, useState} from "react";

export const TaskForm = () => {

    const [text, setText] = useState("Přidat obrázek");

    let addImage = (event) => {
        event.preventDefault()
        if (text === "Přidat obrázek") {
            setText("Odstranit obrázek")
        } else {
            setText("Přidat obrázek")
        }
    }

    const [text2, setText2] = useState("Přidat obrázek");

    let addImage2 = (event) => {
        event.preventDefault()
        if (text2 === "Přidat obrázek") {
            setText2("Odstranit obrázek")
        } else {
            setText2("Přidat obrázek")
        }
    }

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label>Otázka</label>
                <input className={styles.big} type={"text"}/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input className={styles.small} type={"text"}/>}
                <label>Odpověď</label>
                <input className={styles.big} type={"text"}/>
                <button onClick={addImage2} className={styles.image}>{text2}</button>
                {text2 === "Odstranit obrázek" && <input className={styles.small} type={"text"}/>}
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}