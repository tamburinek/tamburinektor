import styles from './CreateQuestion.module.scss'

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";
import {CloseQuestion} from "./closed-question/CloseQuestion";
import {OpenQuestion} from "./open-question/OpenQuestion";
import TestApi from "../../../../../../services/testApi";

export const CreateQuestion = (props) => {

    const [type, setType] = useState("close");
    const [text, setText] = useState("Odstranit obrázek");

    const [question, setQuestion] = useState("");
    const [imageLink, setImage] = useState("");

    const [right, setRight] = useState([])
    const [wrong, setWrong] = useState([])

    let addImage = (event) => {
        event.preventDefault()
        if (text === "Přidat obrázek") {
            setText("Odstranit obrázek")
        } else {
            setText("Přidat obrázek")
            setImage("")
        }
    }

    useEffect(() => {
        if (props.id !== undefined){
            TestApi.getAssignmentById(props.id).then((res) => {
                setQuestion(res.data.question)
                setImage(res.data.imageLink)
                setRight(res.data.rightAnswers)
                setWrong(res.data.wrongAnswers)
                setType(res.data.openQuestion ? "open" : "close")
            })
        }
    },[])

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.form} onClick={(event => event.stopPropagation())}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                <h1>Vytvořit otázku</h1>
                <h2>Typ otázky</h2>
                <select className={styles.select} onChange={(e) => {
                    setType(e.target.value);
                }}>
                    <option value={"close"}>Uzavřená otázka</option>
                    <option value={"open"}>Otevřená otázka</option>
                </select>
                <h2>Otázka</h2>
                <input onChange={(e) => {
                    setQuestion(e.target.value)
                }} value={question} className={styles.input}/>
                <button onClick={addImage} className={styles.image}>{text}</button>
                {text === "Odstranit obrázek" && <input onChange={(e) => {
                    setImage(e.target.value)
                }} value={imageLink} className={styles.input} placeholder={"Obrázek"} type={"text"}/>}
                <div className={styles.modal}>
                    {type === "close" && <CloseQuestion id={props.id} right={right} wrong={wrong} onAdd={() => {
                        setQuestion("")
                        setImage("")
                    }} onEdit={() => props.onClose()} question={question} image={imageLink}/>}
                    {type === "open" && <OpenQuestion id={props.id} right={right} onAdd={() => {
                        setQuestion("")
                        setImage("")
                    }} onEdit={() => props.onClose()} question={question} image={imageLink}/>}
                </div>
            </div>
        </div>
    )
}