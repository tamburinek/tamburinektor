import styles from "./OpenQuestion.module.scss"
import {useEffect, useState} from "react";
import TestApi from "../../../../../../../services/testApi";

export const OpenQuestion = (props) => {

    const [answer, setAnswer] = useState("");

    let confirm = (event) => {
        event.preventDefault()
        if (answer.length < 1){
            return
        }
        TestApi.createOpenTestQuestion(props.question, props.image, answer).then(() => {
            setAnswer("")
            props.onAdd()
        })
    }

    return(
        <div className={styles.main}>
            <h2>Odpověď</h2>
            <input onChange={(e) => {
                setAnswer(e.target.value)
            }} value={answer} className={styles.input}/>
            <button onClick={confirm} className={styles.add}>Vytvořit</button>
        </div>
    )
}