import styles from './CreateMaterialModal.module.scss'
import {DefinitionForm} from "../../../../../../parts/lecture-types/definition-form/DefinitionForm";

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";

export const CreateMaterialModal = (props) => {

    const [type, setType] = useState("definition");

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.form} onClick={event => event.stopPropagation()}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                <h1 className={styles.h1}>Vytvořit materiál</h1>
                <label className={styles.typeText}>Typ materiálu</label>
                <select onChange={(e) => {
                        setType(e.target.value);
                    }}
                    className={styles.selection} id="typ" name="type">
                    <option value="definition">Definice</option>
                    <option value="graph">Graf</option>
                    <option value="image">Obrázek</option>
                    <option value="question">Otázka</option>
                    <option value="task">Úkol</option>
                    <option value="quiz">Quiz</option>
                </select>
                {type === "definition" && <DefinitionForm/>}
            </div>
        </div>
    )
}