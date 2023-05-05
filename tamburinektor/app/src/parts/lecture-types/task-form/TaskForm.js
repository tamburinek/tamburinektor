import styles from "./TaskForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";

export const TaskForm = () => {

    const [text, setText] = useState("Přidat obrázek");

    const [question, setQuestion] = useState('');
    const [answer, setAnswer] = useState('');
    const [questionImage, setQuestionImage] = useState('');
    const [answerImage, setAnswerImage] = useState('');

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
        MaterialsApi.createTask(question, questionImage, answer, answerImage).then(() => {
            setQuestion("")
            setAnswer("")
            setQuestionImage("")
            setAnswerImage("")
        })
    }

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label>Otázka</label>
                <input onChange={(e) => {
                    setQuestion(e.target.value)}}
                       value={question} className={styles.big} type={"text"}/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input onChange={(e) => {
                    setQuestionImage(e.target.value)}} value={questionImage} className={styles.small} type={"text"}/>}
                <label>Odpověď</label>
                <input onChange={(e) => {
                    setAnswer(e.target.value)}}
                       value={answer} className={styles.big} type={"text"}/>
                <button onClick={addImage2} className={styles.image}>{text2}</button>
                {text2 === "Odstranit obrázek" && <input onChange={(e) => {
                    setAnswerImage(e.target.value)}} value={answerImage} className={styles.small} type={"text"}/>}
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}