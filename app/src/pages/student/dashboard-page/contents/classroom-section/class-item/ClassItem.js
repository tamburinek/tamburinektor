import styles from "./ClassItem.module.scss"
import {useEffect, useState} from "react";

export const ClassItem = (props) => {

    const [activeDiv, setActiveDiv] = useState(-1)

    let active = styles.main + " " + styles.active
    let nonActive = styles.main

    let contains = (array) => {
        for (const item of array) {
            if (item.id === props.id){
                return true
            }
        }
        return false
    }

    useEffect(() => {
        setActiveDiv(props.current)
    },[props])

    if (contains(props.my)){
        return false
    }

    return(
        <div onClick={props.onClick} className={props.id === activeDiv ? active : nonActive}>
            <span className={styles.span}>{props.name}</span>
            <span className={styles.span2}>{props.username}</span>
        </div>
    )
}