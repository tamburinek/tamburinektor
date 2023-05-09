import styles from "./QuestionEntity.module.scss"
import {useEffect, useState} from "react";
import MaterialsListApi from "../../../../../services/materialsListApi";

export const QuestionEntity = (props) => {

    const [entity, setEntity] = useState({})

    useEffect(() => {
        MaterialsListApi.getQuestionById(props.id).then(res => {
            console.log(res.data)
            setEntity(res.data)
        })
    },[])

    //todo get answers from students

    return(
        <div className={styles.main}>
            <div className={styles.description}>
                {entity.questionText}
            </div>
            <div className={styles.answers}>

            </div>
        </div>
    )
}