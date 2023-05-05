import styles from "./QuestionForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";

export const QuestionForm = () => {

    const [question, setQuestion] = useState('');
    const [anonymous, setAnonymous] = useState(false);

    let confirm = (event) => {
        event.preventDefault()
        MaterialsApi.createQuestion(question, anonymous).then(() => {
            setQuestion("")
            setAnonymous(false)
        })
    }

    const handleChange = (event) => {
        setAnonymous(event.target.checked);
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label className={styles.label}>Otázka</label>
                <input onChange={(e) => {
                        setQuestion(e.target.value)}}
                    value={question} className={styles.question} type={"text"}/>
                <div className={styles.div}>
                    <label className={styles.label} htmlFor={"anonymous"}>Anonymní</label>
                    <input onChange={handleChange} checked={anonymous} className={styles.check} type={"checkbox"} name={"anonymous"} id={"anonymous"}/>
                </div>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}