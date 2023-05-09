import styles from "./LectureEntity.module.scss"

import {useEffect} from "react";

export const LectureEntity = (props) => {

    const active = styles.main + " " + styles.active
    const nonActive = styles.main

    useEffect(() => {

    },[props.current])

    return(
        <div onClick={props.onClick} className={props.id === props.current ? active : nonActive}>
            {props.type === "definition" && <div className={styles.green}>
                D
            </div>}
            {props.type === "image" && <div className={styles.red}>
                I
            </div>}
            {props.type === "question" && <div className={styles.blue}>
                O
            </div>}
            {props.type === "graph" && <div className={styles.brown}>
                G
            </div>}
            {props.type === "task" && <div className={styles.white}>
                Ú
            </div>}
            {props.type === "quiz" && <div className={styles.purple}>
                Q
            </div>}
        </div>
    )
}