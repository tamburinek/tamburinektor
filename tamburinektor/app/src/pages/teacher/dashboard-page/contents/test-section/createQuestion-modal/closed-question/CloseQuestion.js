import styles from "./CloseQuestion.module.scss"
import {useEffect, useState} from "react";
import TestApi from "../../../../../../../services/testApi";

export const CloseQuestion = (props) => {

    const [questions, setQuestions] = useState(4);

    const [first, setFirst] = useState("");
    const [second, setSecond] = useState("");
    const [third, setThird] = useState("");
    const [forth, setForth] = useState("");

    const [firstRight, setFirstRight] = useState(false);
    const [secondRight, setSecondRight] = useState(false);
    const [thirdRight, setThirdRight] = useState(false);
    const [forthRight, setForthRight] = useState(false);

    let addQuestion = (event) => {
        event.preventDefault()
        if (questions < 4){
           setQuestions(questions + 1)
        }
    }

    let removeQuestion = (event) => {
        event.preventDefault()
        if (questions > 2){
            setQuestions(questions - 1)
        }
    }

    let confirm = (event) => {
        event.preventDefault()
        let rightAnswers = []
        let wrongAnswers = []
        if (firstRight){
            rightAnswers.push(first)
        } else {
            wrongAnswers.push(first)
        }
        if (secondRight){
            rightAnswers.push(second)
        } else {
            wrongAnswers.push(second)
        }
        if (thirdRight){
            rightAnswers.push(third)
        } else {
            wrongAnswers.push(third)
        }
        if (forthRight){
            rightAnswers.push(forth)
        } else {
            wrongAnswers.push(forth)
        }
        TestApi.createCloseTestQuestion(props.question, props.image, rightAnswers, wrongAnswers).then(() => {
            setFirst("")
            setSecond("")
            setThird("")
            setForth("")
            setFirstRight(false)
            setSecondRight(false)
            setThirdRight(false)
            setForthRight(false)
            props.onAdd()
        })
    }

    let handleClick1 = () => {
        if (firstRight === true){
            setFirstRight(false)
        } else {
            setFirstRight(true)
        }
    }

    let handleClick2 = () => {
        if (secondRight === true){
            setSecondRight(false)
        } else {
            setSecondRight(true)
        }
    }

    let handleClick3 = () => {
        if (thirdRight === true){
            setThirdRight(false)
        } else {
            setThirdRight(true)
        }
    }

    let handleClick4 = () => {
        if (forthRight === true){
            setForthRight(false)
        } else {
            setForthRight(true)
        }
    }

  return(
      <div className={styles.main}>
          <div className={styles.questions}>
              <div className={styles.oneQuestion}>
                  <label>1. odpověď</label>
                  <div className={styles.line}>
                      <input onChange={(e) => setFirst(e.target.value)}
                             value={first} className={styles.answer}/>
                      <input onClick={handleClick1} checked={firstRight} className={styles.check} type={"checkbox"}/>
                  </div>
              </div>
              <div className={styles.oneQuestion}>
                  <label>2. odpověď</label>
                  <div className={styles.line}>
                      <input onChange={(e) => setSecond(e.target.value)}
                             value={second} className={styles.answer}/>
                      <input onClick={handleClick2} checked={secondRight} className={styles.check} type={"checkbox"}/>
                  </div>
              </div>
          </div>
          <div className={styles.questions}>
              {questions >= 3 &&
                  <div className={styles.oneQuestion}>
                      <label>3. odpověď</label>
                      <div className={styles.line}>
                          <input onChange={(e) => setThird(e.target.value)}
                                 value={third} className={styles.answer}/>
                          <input onClick={handleClick3} checked={thirdRight} className={styles.check} type={"checkbox"}/>
                      </div>
                  </div>
              }
              {questions >= 4 &&
                  <div className={styles.oneQuestion}>
                      <label>4. odpověď</label>
                      <div className={styles.line}>
                          <input onChange={(e) => setForth(e.target.value)}
                                 value={forth} className={styles.answer}/>
                          <input onClick={handleClick4} checked={forthRight} className={styles.check} type={"checkbox"}/>
                      </div>
                  </div>
              }
          </div>
          <div className={styles.buttons}>
              <button className={styles.add} onClick={addQuestion}>Přidat odpověď</button>
              <button className={styles.remove} onClick={removeQuestion}>Smazat odpověď</button>
          </div>
          <button onClick={confirm} className={styles.create}>Vytvořit</button>
      </div>
  )
}