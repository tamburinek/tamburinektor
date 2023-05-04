import styles from "./DefinitionForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";

export const DefinitionForm = (props) => {

    const [text, setText] = useState("Přidat obrázek");
    const [description, setDescription] = useState('');
    const [definition, setDefinition] = useState('');
    const [imageUrl, setImage] = useState('');

    let confirm = (event) => {
        event.preventDefault()
        MaterialsApi.createDefinition(description, definition, imageUrl).then(r => {
            setDefinition("")
            setDescription("")
            setImage("")
        })
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
                <input onChange={(e) => {
                    setDescription(e.target.value)}}
                       value={description}
                       className={styles.description} type={"text"}/>
                <label className={styles.descr}>Definice</label>
                <textarea
                    onChange={(e) => {
                        setDefinition(e.target.value)}}
                    value={definition}
                    className={styles.area} name="allDef" id="allDef" cols="30" rows="10"/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input onChange={(e) => {
                    setImage(e.target.value)}} value={imageUrl} className={styles.inputImage}/>}
                <button onClick={confirm} className={styles.add}>Přidat</button>
            </form>
        </div>
    )
}