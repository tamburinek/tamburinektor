import styles from './LectureTeacherPage.module.scss'
import {useEffect, useState} from "react";
import {Link, useLocation} from "react-router-dom";
import logo from "../../../assets/png/logo.png";
import LectureApi from "../../../services/lectureApi";
import {LectureEntity} from "./lecture-entity/LectureEntity";
import {DefinitionEntity} from "./lecture-entities/definition-entity/DefinitionEntity";
import {ImageEntity} from "./lecture-entities/image-entity/ImageEntity";
import {QuestionEntity} from "./lecture-entities/question-entity/QuestionEntity";
import {TaskEntity} from "./lecture-entities/task-entity/TaskEntity";
import {useBeforeunload} from 'react-beforeunload';


export const LectureTeacherPage = () => {

    //methods for location
    const location = useLocation();
    const params = new URLSearchParams(location.search)

    const [lectureDescription, setLectureDescription] = useState("")
    const [lectureEntities, setLectureEntities] = useState([])

    const [index, setIndex] = useState(0)
    const [currentEntity, setCurrentEntity] = useState({})
    const [fetched, setFetched] = useState(false)
    const [ready, setReady] = useState(false)

    //todo set lecture as not active
    useBeforeunload(() => console.log("ahoj"));

    let listItems = lectureEntities.filter(item => item).map(item => {
        return <LectureEntity key={item.id} id={item.id} current={currentEntity.id} onClick={() => setCurrentEntity(item)} type={item.lectureType}/>
    })

    useEffect(()=> {
        LectureApi.getLectureById(params.get("id")).then(res => {
            console.log(res.data)
            setLectureDescription(res.data.description)
            setLectureEntities(res.data.lectureEntities)
            setFetched(true)
        })
    },[])

    useEffect(() => {
        if (fetched === false) {
            return
        }
        setCurrentEntity(lectureEntities[0])
        console.log(lectureEntities)
        setReady(true)
    },[fetched])

    useEffect(() => {
        if (currentEntity === 0){
            return
        }
    },[currentEntity])

    return (
        <div className={styles.main}>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <div className={styles.sidebar}>
                <span className={styles.span}>Plán lekce</span>
                <div className={styles.allItems}>
                    {ready === true && listItems}
                </div>
            </div>
            <div className={styles.lecture}>
                <div className={styles.lectureDescription}>
                    {lectureDescription}
                </div>
                <div className={styles.lectureItem}>
                    {ready === true && currentEntity.lectureType === "definition" && <DefinitionEntity key={currentEntity.id} id={currentEntity.id}/>}
                    {ready === true && currentEntity.lectureType === "image" && <ImageEntity key={currentEntity.id} id={currentEntity.id}/>}
                    {ready === true && currentEntity.lectureType === "question" && <QuestionEntity key={currentEntity.id} id={currentEntity.id}/>}
                    {ready === true && currentEntity.lectureType === "task" && <TaskEntity key={currentEntity.id} id={currentEntity.id}/>}
                </div>
            </div>
        </div>
    )
}