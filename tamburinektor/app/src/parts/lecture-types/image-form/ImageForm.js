import styles from "./ImageForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";
import MaterialsListApi from "../../../services/materialsListApi";

export const ImageForm = (props) => {

    const [description, setDescription] = useState('');
    const [imageUrl, setImage] = useState('');

    const [buttonText, setButtonText] = useState("Přidat");

    let confirm = (event) => {
        event.preventDefault()
        if (props.id !== undefined){
            MaterialsApi.updateImage(props.id, description, imageUrl).then(() => {
                props.onEdit()
            })
        }else {
            MaterialsApi.createImage(description, imageUrl).then(() => {
                setDescription("")
                setImage("")
            })
        }
    }

    useEffect(() => {
        if (props.id !== undefined){
            MaterialsListApi.getImageById(props.id).then(response => {
                setDescription(response.data.description)
                setImage(response.data.imageUrl)
            })
            setButtonText("Aktualizovat")
        }
    }, [props.id])

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label className={styles.label}>Popis obrázku</label>
                <input onChange={(e) => {
                    setDescription(e.target.value)}}
                       value={description} className={styles.description} type={"text"}/>
                <label className={styles.label}>Odkaz</label>
                <input value={imageUrl} onChange={(e) => {
                    setImage(e.target.value)}} className={styles.link} type={"text"}/>
                <button onClick={confirm} className={styles.add}>{buttonText}</button>
            </form>
        </div>
    )
}