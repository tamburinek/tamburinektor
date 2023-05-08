import styles from "./TaskForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";
import MaterialsListApi from "../../../services/materialsListApi";

export const TaskForm = (props) => {

    const [text, setText] = useState("Odstranit obrázek");

    const [question, setQuestion] = useState('');
    const [answer, setAnswer] = useState('');
    const [questionImage, setQuestionImage] = useState('');
    const [answerImage, setAnswerImage] = useState('');
    const [buttonText, setButtonText] = useState("Vytvořit");

    let addImage = (event) => {
        event.preventDefault()
        if (text === "Přidat obrázek") {
            setText("Odstranit obrázek")
        } else {
            setText("Přidat obrázek")
            setQuestionImage('')
        }
    }

    const [text2, setText2] = useState("Odstranit obrázek");

    let addImage2 = (event) => {
        event.preventDefault()
        if (text2 === "Přidat obrázek") {
            setText2("Odstranit obrázek")
        } else {
            setText2("Přidat obrázek")
            setAnswerImage('')
        }
    }

    let confirm = (event) => {
        event.preventDefault()
        if (props.id !== undefined){
            MaterialsApi.updateTask(props.id, question, questionImage, answer, answerImage).then(() => {
                props.onEdit()
            })
        }else {
            MaterialsApi.createTask(question, questionImage, answer, answerImage).then(() => {
                setQuestion("")
                setAnswer("")
                setQuestionImage("")
                setAnswerImage("")
        })}
    }

    useEffect(() => {
        if (props.id !== undefined){
            MaterialsListApi.getTaskById(props.id).then(response => {
                setQuestion(response.data.question)
                setAnswer(response.data.answer)
                setQuestionImage(response.data.questionImage)
                setAnswerImage(response.data.answerImage)
            })
            setButtonText("Aktualizovat")
        }
    }, [props.id])

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <label>Otázka</label>
                <textarea onChange={(e) => {
                    setQuestion(e.target.value)}}
                       value={question} className={styles.big}/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input onChange={(e) => {
                    setQuestionImage(e.target.value)}} value={questionImage} className={styles.small} type={"text"}/>}
                <label>Odpověď</label>
                <textarea onChange={(e) => {
                    setAnswer(e.target.value)}}
                       value={answer} className={styles.big} />
                <button onClick={addImage2} className={styles.image}>{text2}</button>
                {text2 === "Odstranit obrázek" && <input onChange={(e) => {
                    setAnswerImage(e.target.value)}} value={answerImage} className={styles.small} type={"text"}/>}
                <button onClick={confirm} className={styles.add}>{buttonText}</button>
            </form>
        </div>
    )
}