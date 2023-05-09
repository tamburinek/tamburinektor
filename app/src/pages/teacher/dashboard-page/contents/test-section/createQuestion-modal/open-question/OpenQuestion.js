import styles from "./OpenQuestion.module.scss"
import {useEffect, useState} from "react";
import TestApi from "../../../../../../../services/testApi";

export const OpenQuestion = (props) => {

    const [answer, setAnswer] = useState("");
    const [buttonText, setButtonText] = useState("Vytvořit");

    let confirm = (event) => {
        event.preventDefault()
        if (answer.length < 1){
            return
        }
        if (props.id !== undefined){
            TestApi.updateOpenQuestion(props.id, props.question, props.image, answer).then(() => {
                props.onEdit()
            })
        } else {
            TestApi.createOpenTestQuestion(props.question, props.image, answer).then(() => {
                setAnswer("")
                props.onAdd()
            })
        }
    }

    useEffect(() => {
        if (props.id !== undefined) {
            setAnswer(props.right[0])
            setButtonText("Aktualizovat")
        }
    },[])

    return(
        <div className={styles.main}>
            <h2>Odpověď</h2>
            <input onChange={(e) => {
                setAnswer(e.target.value)
            }} value={answer} className={styles.input}/>
            <button onClick={confirm} className={styles.add}>{buttonText}</button>
        </div>
    )
}