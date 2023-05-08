import styles from "./MyClassItem.module.scss"
import {useEffect, useState} from "react";

export const MyClassItem = (props) => {

    return(
        <div onClick={props.onClick} className={styles.main}>
            <span className={styles.span}>{props.name}</span>
        </div>
    )
}