import styles from './TestSection.module.scss'
import {CreateSquare} from "../../../../../parts/squares/create-square/CreateSquare"
import {AllSquare} from "../../../../../parts/squares/all-square/AllSquare"
import {CountSquare} from "../../../../../parts/squares/count-square/CountSquare"
import {GraphSquare} from "../../../../../parts/squares/graph-square/GraphSquare"
import {LastSquare} from "../../../../../parts/squares/last-square/LastSquare"

import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {CreateMaterialModal} from "../materials-section/createMaterials-modal/CreateMaterialModal";
import {CreateQuestion} from "./createQuestion-modal/CreateQuestion";

export const TestSection = () => {

    const [createModalVisible, setCreateVisible] = useState(false)

    return (
        <div className={styles.main}>
            <Link to={'/test/creation'}><CreateSquare text={"Vytvořit test"}/></Link>
            <Link><AllSquare text={"Zobrazit vše"}/></Link>
            <Link onClick={() => setCreateVisible(true)}>
                <CountSquare text={"Vytvořit otázku"}/>
            </Link>
            {createModalVisible === true && <CreateQuestion onClose={() => setCreateVisible(false)}/>}
            <Link><LastSquare text={"TODO"}/></Link>
            <Link><GraphSquare text={"TODO"}/></Link>
        </div>
    )
}