import styles from "./QuestionForm.module.scss"
import {useEffect, useState} from "react";
import MaterialsApi from "../../../services/materialsApi";
import MaterialsListApi from "../../../services/materialsListApi";

export const QuestionForm = (props) => {

    const [question, setQuestion] = useState('');
    const [buttonText, setButtonText] = useState("Vytvořit");
    const [anonymous, setAnonymous] = useState(false);

    let confirm = (event) => {
        event.preventDefault()
        if (props.id !== undefined){
            MaterialsApi.updateQuestion(props.id, question, anonymous).then(() => {
                props.onEdit()
            })
        }else {
            MaterialsApi.createQuestion(question, anonymous).then(() => {
                setQuestion("")
                setAnonymous(false)
            })
        }
    }

    const handleChange = (event) => {
        setAnonymous(event.target.checked);
    }

    useEffect(() => {
        if (props.id !== undefined){
            MaterialsListApi.getQuestionById(props.id).then(response => {
                setQuestion(response.data.questionText)
                setAnonymous(response.data.anonymous)
            })
            setButtonText("Aktualizovat")
        }
    }, [props.id])

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
                <button onClick={confirm} className={styles.add}>{buttonText}</button>
            </form>
        </div>
    )
}