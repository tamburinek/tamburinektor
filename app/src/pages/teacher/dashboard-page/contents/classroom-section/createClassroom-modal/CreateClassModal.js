import styles from './CreateClassModal.module.scss'

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";
import ClassRoomApi from "../../../../../../services/classRoomApi";

export const CreateClassModal = (props) => {

    const [passwordVis, setPasswordVis] = useState(false);
    const [passwordText, setPasswordText] = useState("Požadovat heslo");

    const [name, setName] = useState("");
    const [password, setPassword] = useState("");

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

    let confirm = (event) => {
        event.preventDefault()
        ClassRoomApi.createClass(name, password).then(() => {
            setName("")
            setPassword("")
        })
    }

    return (
        <div className={styles.main} onClick={props.onClose}>
            <form className={styles.form} onClick={event => event.stopPropagation()}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                <h1 className={styles.h1}>Vytvořit třídu</h1>
                <input onChange={(e) => {setName(e.target.value)}}
                       value={name} className={styles.name} placeholder={"Název třídy"}/>
                {passwordVis === true &&
                    <input onChange={(e) => {setPassword(e.target.value)}}
                           className={styles.name + " " + styles.pass} value={password} placeholder={"Heslo"}/>}
                <span onClick={() => toggle()} className={styles.label}> {passwordText} </span>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}