import styles from './CreateQuestion.module.scss'

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";
import {CloseQuestion} from "./closed-question/CloseQuestion";
import {OpenQuestion} from "./open-question/OpenQuestion";

export const CreateQuestion = (props) => {

    const [type, setType] = useState("close");

    const [text, setText] = useState("Přidat obrázek");

    let addImage = (event) => {
        event.preventDefault()
        if (text === "Přidat obrázek") {
            setText("Odstranit obrázek")
        } else {
            setText("Přidat obrázek")
        }
    }

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.form} onClick={(event => event.stopPropagation())}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                <h1>Vytvořit otázku</h1>
                <h2>Typ otázky</h2>
                <select className={styles.select} onChange={(e) => {
                    setType(e.target.value);
                }}>
                    <option value={"close"}>Uzavřená otázka</option>
                    <option value={"open"}>Otevřená otázka</option>
                </select>
                <h2>Otázka</h2>
                <input className={styles.input}/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input className={styles.input} placeholder={"Obrázek"} type={"text"}/>}
                <div className={styles.modal}>
                    {type === "close" && <CloseQuestion/>}
                    {type === "open" && <OpenQuestion/>}
                </div>
                <button onClick={confirm} className={styles.add}>Vytvořit</button>
            </div>
        </div>
    )
}