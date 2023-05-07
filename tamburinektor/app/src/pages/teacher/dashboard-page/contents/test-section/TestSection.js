import styles from './TestSection.module.scss'
import {CreateSquare} from "../../../../../parts/squares/create-square/CreateSquare"
import {AllSquare} from "../../../../../parts/squares/all-square/AllSquare"
import {CountSquare} from "../../../../../parts/squares/count-square/CountSquare"
import {GraphSquare} from "../../../../../parts/squares/graph-square/GraphSquare"
import {LastSquare} from "../../../../../parts/squares/last-square/LastSquare"

import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {CreateQuestion} from "./createQuestion-modal/CreateQuestion";
import {ViewAllTests} from "./viewAll-modal/ViewAllTests";
import TestApi from "../../../../../services/testApi";

export const TestSection = () => {

    const [createModalVisible, setCreateVisible] = useState(false)
    const [viewAllTestsVisible, setAllTestVisible] = useState(false)
    const [lastTestText, setLastTestText] = useState(false)

    useEffect(() => {
        TestApi.getLastTest().then((res) => {
            if (res.data.description === ""){
                return
            }
            setLastTestText(res.data.description)
        })
    })

    return (
        <div className={styles.main}>
            <Link to={'/test/creation'}><CreateSquare text={"Vytvořit test"}/></Link>
            <Link onClick={() => setAllTestVisible(true)}><AllSquare text={"Zobrazit vše"}/></Link>
            {viewAllTestsVisible === true && <ViewAllTests onClose={() => setAllTestVisible(false)}/>}
            <Link onClick={() => setCreateVisible(true)}>
                <CountSquare text={"Vytvořit otázku"}/>
            </Link>
            {createModalVisible === true && <CreateQuestion onClose={() => setCreateVisible(false)}/>}
            <LastSquare text={lastTestText}/>
            <Link><GraphSquare text={"TODO"}/></Link>
        </div>
    )
}