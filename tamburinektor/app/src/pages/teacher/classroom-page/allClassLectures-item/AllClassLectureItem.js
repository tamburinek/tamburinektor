import styles from './AllClassLectureItem.module.scss'
import {Link} from "react-router-dom";
import close from "../../../../assets/png/close.png"

import {useEffect, useState} from "react";


export const AllClassLectureItem = (props) => {

    useEffect(() => {

    },[])

    return(
        <div className={styles.main}>
            <span className={styles.text}>{props.description}</span>
            <img onClick={props.click} className={styles.plus} src={close} alt={"close"}/>
        </div>
    )
}