import styles from "./TestStudentPage.module.scss"
import {Link, useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import TestApi from "../../../services/testApi";
import logo from "../../../assets/png/logo.png";


export const TestStudentPage = () => {

  //methods for location
  const location = useLocation();
  const params = new URLSearchParams(location.search)

  const [index, setIndex] = useState(0)
  const [questionLen, setQuestionLen] = useState(-1)

  const [inputUser, setUserInput] = useState("")
  const [testDescription, setTestDescription] = useState("")
  const [imageSrc, setImageSrc] = useState("")
  const [isOpenQuestion, setOpenQuesion] = useState(false)

  const [questions, setQuestions] = useState([])

  const [fetched, setFetched] = useState(false)
  const [answered, setAnswered] = useState(false)

  const handleClick = event => {
    event.currentTarget.classList.toggle(styles.active);
  }

  let moveIndex = () => {
    setUserInput("")
    let helper = index
    helper = helper + 1
    if (helper < questionLen){
      setIndex(helper)
      setImageSrc(questions[helper].imageLink)
      setOpenQuesion(questions[helper].openQuestion)
      if (questions[helper].openQuestion === true){
        setRight(questions[helper].rightAnswers[0])
      } else {
        setRightAnswers(questions[helper].rightAnswers)
        setWrongAnswers(questions[helper].wrongAnswers)
      }
    }else{
      setIndex(0)
      setImageSrc(questions[0].imageLink)
      setOpenQuesion(questions[0].openQuestion)
      handleQuestion()
    }
  }

  let handleQuestion = () => {
    setOpenQuesion(questions[0] === true)
    if (questions[0] === true){
      setRight(questions[0].rightAnswers[0])
    } else {
      setRightAnswers(questions[0].rightAnswers)
      setWrongAnswers(questions[0].wrongAnswers)
    }
  }

  useEffect(() => {
    TestApi.getTestById(params.get("id")).then((res) => {
      setTestDescription(res.data.description)
      setQuestions(res.data.assignments)
      setQuestionLen(res.data.assignments.length)
      for (const item of res.data.assignments) {
        let helper = questions
        TestApi.getAssignmentById(item).then((res) => {
          helper.push(res.data)
          setQuestions(helper)
          setFetched(true)
        })
      }
    })
  }, [])

  useEffect(() => {
    if (fetched === false){
      return
    }
    handleQuestion()
  }, [fetched])

  const [right, setRight] = useState("")
  const [rightAnswers, setRightAnswers] = useState([])
  const [wrongAnswers, setWrongAnswers] = useState([])

  let listAnswers = rightAnswers.filter(item => item).map(item => {
    return <div key={item} onClick={handleClick} className={styles.oneAnswer}> {item} </div>
  })

  let listAnswers2 = wrongAnswers.filter(item => item).map(item => {
    return <div key={item} onClick={handleClick} className={styles.oneAnswer}> {item} </div>
  })

  return(
      <div className={styles.main}>
        <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
        <div className={styles.description}>
            <h1>{testDescription}</h1>
        </div>
        <div className={styles.question}>
          <div className={styles.questionCount}>
            {index+1 + " / " + questionLen}
          </div>
          <div className={styles.oneQuestion}>
            {questionLen > 0 && fetched===true && <div className={styles.oneQuestion}> {questions[index].question} </div>}
            {imageSrc !== "" && <img className={styles.image} src={imageSrc} alt={"question"}/>}
          </div>
          <div className={styles.answer}>

            {isOpenQuestion === false && answered === false && fetched === true && listAnswers}
            {isOpenQuestion === false && answered === false && fetched === true && listAnswers2}
            {isOpenQuestion === false && answered === true && listAnswers}
            {isOpenQuestion === true && answered === false && <input onChange={event => setUserInput(event.target.value)} value={inputUser} className={styles.userInput} placeholder={"odpověď"}/>}
            {isOpenQuestion === true && fetched === true && answered === true && <span className={styles.span}>{right}</span>}
          </div>
        </div>
        <div className={styles.buttons}>
          {answered === true && <button onClick={() => {
            moveIndex()
            setAnswered(false)
          }} className={styles.answerButton}>Další</button>}
          {answered === false && <button onClick={() => setAnswered(true)} className={styles.answerButton}>Odpovědět</button>}
          <button onClick={() => window.location = "/dashboard"} className={styles.exit}>Ukončit test</button>
        </div>
      </div>
  )
}