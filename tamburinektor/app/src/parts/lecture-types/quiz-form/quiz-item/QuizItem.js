import styles from "./QuizItem.module.scss"
import close from "../../../../assets/png/close.png"
import pencil from "../../../../assets/png/pencil-grey.png"

import {useEffect, useState} from "react";

export const QuizItem = (props) => {


    return (
        <div className={styles.main}>
            <span className={styles.name}>{props.item}</span>
            <div className={styles.images}>
                <img className={styles.pencil} src={pencil} alt={"pencil"}/>
                <img className={styles.close} src={close} alt={"close"}/>
            </div>
        </div>
    )
}