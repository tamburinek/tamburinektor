import styles from './AllLectureItem.module.scss'
import {Link} from "react-router-dom";
import plus from "../../../../assets/png/plus.png"

import {useEffect, useState} from "react";


export const AllLectureItem = (props) => {

    return(
        <div className={styles.main}>
            <span className={styles.text}>{props.description}</span>
            <img onClick={props.click} className={styles.plus} src={plus} alt={"plus"}/>
        </div>
    )
}