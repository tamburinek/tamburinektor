import styles from './CreateQuestion.module.scss'

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";
import {CloseQuestion} from "./closed-question/CloseQuestion";
import {OpenQuestion} from "./open-question/OpenQuestion";

export const CreateQuestion = (props) => {

    const [type, setType] = useState("close");
    const [points, setPoints] = useState(1);

    let updatePoints = (event) => {
        setPoints(event.target.value)
    }

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.form} onClick={(event => event.stopPropagation())}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                <h1>Vytvořit otázku</h1>
                <h2>Typ otázky</h2>
                <select onChange={(e) => {
                    setType(e.target.value);
                }}>
                    <option value={"close"}>Uzavřená otázka</option>
                    <option value={"open"}>Otevřená otázka</option>
                </select>
                <h2>Otázka</h2>
                <input/>
                <h2>Počet bodů = {points}</h2>
                <input onChange={(event) => updatePoints(event)} type={"range"} name={"range"} min={1} max={10} step={1} defaultValue={1}/>
                <button>Přidat obrázek</button>
                <input/>
                {type === "close" && <CloseQuestion/>}
                {type === "open" && <OpenQuestion/>}
            </div>
        </div>
    )
}