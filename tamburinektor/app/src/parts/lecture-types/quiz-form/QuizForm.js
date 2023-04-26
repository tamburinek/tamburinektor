import styles from "./QuizForm.module.scss"

import {useState} from "react";
import {QuizItem} from "./quiz-item/QuizItem";

export const QuizForm = () => {

    const [answersCount, setAnswersCount] = useState(2);

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
    }

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3,5,3,4]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <QuizItem item={item}/>
    );

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <div className={styles.popisek}>
                    <label className={styles.text}>Popis quizu</label>
                    <input className={styles.name} type={"text"}/>
                </div>
                <div className={styles.bottom}>
                    <div className={styles.create}>
                        <div className={styles.createName}>
                            <span className={styles.span}>Vytvořit otázku</span>
                        </div>
                        <div className={styles.answers}>
                            <div className={styles.answersWithout}>
                                <input className={styles.question} type={"text"} placeholder={"Otázka"}/>
                                <input className={styles.answer} placeholder={"První odpověď"}/>
                                <input className={styles.answer} placeholder={"Druhá odpověď"}/>
                                {answersCount > 2 && <input className={styles.answer} placeholder={"Třetí odpověď"}/>}
                                {answersCount > 3 && <input className={styles.answer} placeholder={"Čtvrtá odpověď"}/>}
                            </div>
                            <div className={styles.buttons}>
                                <button className={styles.addButton} onClick={(event) => addAnswer(event)}>Přidat</button>
                                <button className={styles.removeButton} onClick={(event) => removeAnswer(event)}>Smazat</button>
                            </div>
                        </div>
                        <div className={styles.bottomButton}>
                            <button className={styles.createQuest}>Vytvořit otázku</button>
                        </div>
                    </div>
                    <div className={styles.overview}>
                        <div className={styles.divName}>
                            <span className={styles.questionName}>Otázky</span>
                        </div>
                        <div className={styles.questions}>
                            {listBoxItems}
                        </div>
                    </div>
                </div>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}