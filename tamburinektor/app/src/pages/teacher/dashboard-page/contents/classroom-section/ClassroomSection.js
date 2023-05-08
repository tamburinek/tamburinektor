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
            if (response.data.name === "") {
                return
            }
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
            <Link><GraphSquare text={"Toto je stránka pro správu třídy. Dá se zde vytvořit nová třída (nedá se měnit jméno ani heslo), counter vpravo " +
                "ukazuje počet vytvořených tříd. V pravém horním rohu je název poslední vytvořené třídy a ve Správa tříd lze zpřístupnit lekce a testy pro studenty (do té doby jsou " +
                "viditelné pouze pro učitele - stačí vybrat třídu z nabídky)"}/></Link>
        </div>
    )
}