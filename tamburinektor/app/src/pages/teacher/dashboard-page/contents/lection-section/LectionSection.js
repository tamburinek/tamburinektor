import styles from './LectionSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {CreateSquare} from "../../../../../parts/squares/create-square/CreateSquare";
import {AllSquare} from "../../../../../parts/squares/all-square/AllSquare";
import {CountSquare} from "../../../../../parts/squares/count-square/CountSquare";
import {LastSquare} from "../../../../../parts/squares/last-square/LastSquare";
import {GraphSquare} from "../../../../../parts/squares/graph-square/GraphSquare";
import {CreateMaterialModal} from "../materials-section/createMaterials-modal/CreateMaterialModal";

export const LectionSection = () => {

    const [createModalVisible, setCreateVisible] = useState(false)

    return (
        <div className={styles.main}>
            <Link to={'/lecture/creation'}><CreateSquare text={"Vytvořit lekci"}/></Link>
            <AllSquare text={"Zobrazit vše"}/>
            <Link onClick={() => setCreateVisible(true)}>
                <CountSquare text={"Vytvořit materiál"}/>
            </Link>
            {createModalVisible === true && <CreateMaterialModal onClose={() => setCreateVisible(false)}/>}
            <Link><LastSquare text={"TODO"}/></Link>
            <Link><GraphSquare text={"TODO"}/></Link>
        </div>
    )
}