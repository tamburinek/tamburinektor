import styles from './ClassroomSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {CreateSquare} from "../../../../../parts/squares/create-square/CreateSquare";
import {AllSquare} from "../../../../../parts/squares/all-square/AllSquare";
import {CountSquare} from "../../../../../parts/squares/count-square/CountSquare";
import {LastSquare} from "../../../../../parts/squares/last-square/LastSquare";
import {GraphSquare} from "../../../../../parts/squares/graph-square/GraphSquare";
import {CreateClassModal} from "./createClassroom-modal/CreateClassModal";
import ClassRoomApi from "../../../../../services/classRoomApi";

export const ClassroomSection = () => {

    const [createModalVisible, setCreateVisible] = useState(false)
    const [countClass, setCount] = useState(0)
    const [lastName, setLastName] = useState(undefined)


    useEffect(() => {
        if (createModalVisible === true){
            return
        }
        ClassRoomApi.getCountClass().then((response) => {
            setCount(response.data)
        })
        ClassRoomApi.getLastClass().then((response) => {
            //todo check if is null
            setLastName(response.data.name)
        })
    },[createModalVisible])

    return (
        <div className={styles.main}>
            <Link onClick={() => setCreateVisible(true)}>
                <CreateSquare text={"Vytvořit třídu"}/>
            </Link>
            {createModalVisible === true && <CreateClassModal onClose={() => setCreateVisible(false)}/>}
            <Link to={"/classroom"}><AllSquare text={"Správa tříd"}/></Link>
            <CountSquare text={countClass > 4 ? countClass + " tříd" : countClass + " třídy"}/>
            <LastSquare text={lastName}/>
            <Link><GraphSquare text={"TODO"}/></Link>
        </div>
    )
}