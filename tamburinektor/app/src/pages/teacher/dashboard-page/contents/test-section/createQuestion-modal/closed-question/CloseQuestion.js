import styles from "./CloseQuestion.module.scss"
import {useEffect, useState} from "react";

export const CloseQuestion = () => {

    const [questions, setQuestions] = useState(2);

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

  return(
      <div className={styles.main}>
          <div className={styles.questions}>
              <div className={styles.oneQuestion}>
                  <label>1. odpověď</label>
                  <input/>
              </div>
              <div className={styles.oneQuestion}>
                  <label>2. odpověď</label>
                  <input/>
              </div>
          </div>
          <div className={styles.questions}>
              {questions >= 3 &&
                  <div className={styles.oneQuestion}>
                      <label>3. odpověď</label>
                      <input/>
                  </div>
              }
              {questions >= 4 &&
                  <div className={styles.oneQuestion}>
                      <label>4. odpověď</label>
                      <input/>
                  </div>
              }
          </div>
          <div className={styles.buttons}>
              <button className={styles.add} onClick={addQuestion}>Přidat odpověď</button>
              <button className={styles.remove} onClick={removeQuestion}>Smazat odpověď</button>
          </div>
      </div>
  )
}