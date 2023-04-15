import styles from './CreateClassModal.module.scss'

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";

export const CreateClassModal = (props) => {

    const [passwordVis, setPasswordVis] = useState(false);
    const [passwordText, setPasswordText] = useState("Požadovat heslo");

    let toggle = () => {
        if (passwordVis === true) {
            setPasswordVis(false)
            setPasswordText("Požadovat heslo")
        }
        else {
            setPasswordVis(true)
            setPasswordText("Nepožadovat heslo")
        }
    }

    return (
        <div className={styles.main} onClick={props.onClose}>
            <form className={styles.form} onClick={event => event.stopPropagation()}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                <h1 className={styles.h1}>Vytvořit třídu</h1>
                <input className={styles.name} placeholder={"Název třídy"}/>
                {passwordVis === true && <input className={styles.name + " " + styles.pass} placeholder={"Heslo"}/>}
                <span onClick={() => toggle()} className={styles.label}> {passwordText} </span>
                <button className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}