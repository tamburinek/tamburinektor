import styles from "./TaskEntity.module.scss"
import {useEffect, useState} from "react";
import MaterialsListApi from "../../../../../services/materialsListApi";

export const TaskEntity = (props) => {

    const [entity, setEntity] = useState({})

    const [open, setOpen] = useState(false)
    const [buttonText, setButtonText] = useState("Odkrýt odpověď")

    let handleClick = () => {
        if (open === false) {
            setOpen(true)
            setButtonText("Skrýt odpoěď")
        }else {
            setOpen(false)
            setButtonText("Odkrýt odpověď")
        }

    }

    useEffect(() => {
        MaterialsListApi.getTaskById(props.id).then(res => {
            console.log(res.data)
            setEntity(res.data)
        })
    },[])


    return(
        <div className={styles.main}>
            <div className={styles.question}>
                <div className={styles.questionText}>
                    {entity.question}
                </div>
                <div className={styles.questionImage}>
                    <img className={styles.img} src={entity.questionImage} alt={"taskimage"} onError={event => event.target.style.display = 'none'}/>
                </div>
                <button onClick={handleClick} className={styles.button}>{buttonText}</button>
            </div>

            {open === true && <div className={styles.answer}>
                <div className={styles.questionText}>
                    {entity.answer}
                </div>
                <div className={styles.questionImage}>
                    <img className={styles.img} src={entity.answerImage} alt={"taskimage"} onError={event => event.target.style.display = 'none'}/>
                </div>
            </div>}
        </div>
    )
}