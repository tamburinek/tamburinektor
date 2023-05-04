import styles from "./ImageForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";

export const ImageForm = () => {

    const [description, setDescription] = useState('');
    const [imageUrl, setImage] = useState('');

    let confirm = (event) => {
        event.preventDefault()
        MaterialsApi.createImage(description, imageUrl).then(r => {
            setDescription("")
            setImage("")
        })
    }

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
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}