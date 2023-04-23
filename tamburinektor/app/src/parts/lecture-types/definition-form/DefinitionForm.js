import styles from "./DefinitionForm.module.scss"
import {useEffect, useState} from "react";

export const DefinitionForm = () => {

    const [text, setText] = useState("Přidat obrázek");

    let confirm = (event) => {
        event.preventDefault()
    }

    let addImage = (event) => {
        event.preventDefault()
        if (text === "Přidat obrázek") {
            setText("Odstranit obrázek")
        } else {
            setText("Přidat obrázek")
        }
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label className={styles.descr}>Popis definice</label>
                <input className={styles.description} type={"text"}/>
                <label className={styles.descr}>Definice</label>
                <textarea className={styles.area} name="allDef" id="allDef" cols="30" rows="10"/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input className={styles.inputImage}/>}
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}