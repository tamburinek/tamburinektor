import styles from "./QuizForm.module.scss"

import {useEffect, useState} from "react";
import {QuizItem} from "./quiz-item/QuizItem";
import MaterialsApi from "../../../services/materialsApi";
import MaterialsListApi from "../../../services/materialsListApi";

export const QuizForm = (props) => {

    const [answersCount, setAnswersCount] = useState(4);

    const [description, setDescription] = useState("");
    const [buttonText, setButtonText] = useState("Vytvořit");


    const [question, setQuestion] = useState("");
    const [rightAnswer, setRight] = useState("");
    const [wrongAnswer1, setWrong1] = useState("");
    const [wrongAnswer2, setWrong2] = useState("");
    const [wrongAnswer3, setWrong3] = useState("");


    let addAnswer = (event) => {
        event.preventDefault()
        if (answersCount >= 4) return
        setAnswersCount(answersCount + 1)
    }

    let removeAnswer = (event) => {
        event.preventDefault()
        if (answersCount <= 2) return
        setAnswersCount(answersCount - 1)
    }

    let confirm = (event) => {
        event.preventDefault()
        if (props.id !== undefined){
            MaterialsApi.updateQuiz(props.id, description, boxItems).then(() => {
                props.onEdit()
            })
        } else {
            MaterialsApi.createQuiz(description, boxItems).then((res) => {
                setQuestion("")
                setRight("")
                setWrong1("")
                setWrong2("")
                setWrong3("")
                setAnswersCount(4)
                setDescription("")
                setBoxItems([])
        })}
    }

    let contain = (question) => {
        for (const item of boxItems) {
            if (item.question === question){
                return true;
            }
        }
        return false;
    }

    let createQuestion = (e) => {
        e.preventDefault()
        if (contain(question) || question.length < 2){
            return
        }
        let helper = boxItems
        helper.push({question:question, right:rightAnswer, wrong1:wrongAnswer1, wrong2:wrongAnswer2, wrong3:wrongAnswer3})
        setBoxItems(helper)
        if (rerender === true){
            setRerender(false)
        }else {
            setRerender(true)
        }
        setQuestion("")
        setRight("")
        setWrong1("")
        setWrong2("")
        setWrong3("")
        setAnswersCount(4)
    }

    let deleteItem = (question) => {
        let helper = []
        for (const item of boxItems) {
            if (item.question !== question){
                helper.push(item)
            }
        }
        setBoxItems(helper)
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    }

    let editItem = (question, right, wrong1, wrong2, wrong3) => {
        deleteItem(question)
        setQuestion(question)
        setRight(right)
        setWrong1(wrong1)
        setWrong2(wrong2)
        setWrong3(wrong3)
    }

    useEffect(() => {
        if (props.id !== undefined){
            MaterialsListApi.getQuizById(props.id).then(response => {
                console.log(response.data)
                setDescription(response.data.name)
                setBoxItems(response.data.questions)
            })
            setButtonText("Aktualizovat")
        }
    }, [props.id])

    const [boxItems, setBoxItems] = useState([]);
    const [rerender, setRerender] = useState(false);

    const listBoxItems = boxItems.map((item) =>
        <QuizItem key={item.question} edit={() => editItem(item.question, item.right, item.wrong1, item.wrong2, item.wrong3, item.wrong3)}
                  onDelete={() => deleteItem(item.question)} item={item.question}/>
    );

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <div className={styles.popisek}>
                    <label className={styles.text}>Popis quizu</label>
                    <input onChange={(e) =>{
                        setDescription(e.target.value)
                    }} value={description} className={styles.name} type={"text"}/>
                </div>
                <div className={styles.bottom}>
                    <div className={styles.create}>
                        <div className={styles.createName}>
                            <span className={styles.span}>Vytvořit otázku</span>
                        </div>
                        <div className={styles.answers}>
                            <div className={styles.answersWithout}>
                                <input onChange={(e) =>{
                                    setQuestion(e.target.value)
                                }} value={question} className={styles.question} type={"text"} placeholder={"Otázka"}/>
                                <input value={rightAnswer} onChange={(e) =>{
                                    setRight(e.target.value)
                                }} className={styles.answerRight} placeholder={"Správná odpověď"}/>
                                <input value={wrongAnswer1} onChange={(e) =>{
                                    setWrong1(e.target.value)
                                }} className={styles.answer} placeholder={"Špatná odpověď 1"}/>
                                {answersCount > 2 && <input onChange={(e) =>{
                                    setWrong2(e.target.value)
                                }} value={wrongAnswer2} className={styles.answer} placeholder={"Špatná odpověď 2"}/>}
                                {answersCount > 3 && <input onChange={(e) =>{
                                    setWrong3(e.target.value)
                                }} value={wrongAnswer3} className={styles.answer} placeholder={"Špatná odpověď 3"}/>}
                            </div>
                            <div className={styles.buttons}>
                                <button className={styles.addButton} onClick={(event) => addAnswer(event)}>Přidat</button>
                                <button className={styles.removeButton} onClick={(event) => removeAnswer(event)}>Smazat</button>
                            </div>
                        </div>
                        <div className={styles.bottomButton}>
                            <button onClick={createQuestion} className={styles.createQuest}>Vytvořit otázku</button>
                        </div>
                    </div>
                    <div className={styles.overview}>
                        <div className={styles.divName}>
                            <span className={styles.questionName}>Otázky</span>
                        </div>
                        <div className={styles.questions} autoFocus={rerender}>
                            {listBoxItems}
                        </div>
                    </div>
                </div>
                <button onClick={confirm} className={styles.add}>{buttonText}</button>
            </form>
        </div>
    )
}