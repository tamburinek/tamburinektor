import styles from './MaterialsSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import {CreateSquare} from "../../../../../parts/squares/create-square/CreateSquare";
import {AllSquare} from "../../../../../parts/squares/all-square/AllSquare";
import {CountSquare} from "../../../../../parts/squares/count-square/CountSquare";
import {LastSquare} from "../../../../../parts/squares/last-square/LastSquare";
import {GraphSquare} from "../../../../../parts/squares/graph-square/GraphSquare";
import {CreateMaterialModal} from "./createMaterials-modal/CreateMaterialModal"
import {ViewMaterialsModal} from "./viewMaterials-modal/ViewMaterialsModal";
import {EditMaterialModal} from "./editMaterial-modal/EditMaterialModal";
import CountApi from "../../../../../services/countApi";
import MaterialsApi from "../../../../../services/materialsApi";
import MaterialsListApi from "../../../../../services/materialsListApi";

export const MaterialsSection = () => {

    const [createModalVisible, setCreateVisible] = useState(false)
    const [viewAllModalVisible, setViewAllModalVisible] = useState(false)
    const [editMaterialVisible, setMaterialVisible] = useState(false)
    const [materialType, setMaterialType] = useState("definition")
    const [definitionsCount, setDefinitionsCount] = useState(0)
    const [lastMaterial, setLastMaterial] = useState(undefined)

    let lastMaterialObject

    let handleData = () => {
        if (lastMaterialObject.type === "IMAGE"){
            setLastMaterial("Obrázek - " + lastMaterialObject.description)
        }else if (lastMaterialObject.type === "DEFINITION"){
            setLastMaterial("Definice - " + lastMaterialObject.description)
        }
    }

    useEffect(()=> {
        CountApi.getCountMaterial().then((response) => {
            setDefinitionsCount(response.data)
        })
        MaterialsListApi.getLastCreated().then(response => {
            lastMaterialObject = response.data
            handleData()
        })
    }, [createModalVisible])


    return (
        <div className={styles.main}>
            <Link onClick={() => setCreateVisible(true)}>
                <CreateSquare text={"Vytvořit materiál"}/>
            </Link>
            {createModalVisible === true && <CreateMaterialModal onClose={() => setCreateVisible(false)}/>}
            <Link onClick={() => setViewAllModalVisible(true)}>
                <AllSquare text={"Zobrazit vše"}/>
            </Link>
            {viewAllModalVisible === true && <ViewMaterialsModal
                editMaterial={() => setMaterialVisible(true)}
                onClose={() => setViewAllModalVisible(false)}
                onChange={(type) => setMaterialType(type)}/>}
            {editMaterialVisible === true && <EditMaterialModal type={materialType} onClose={() => setMaterialVisible(false)}/>}
            <CountSquare text={definitionsCount + " materiálů"}/>
            <LastSquare onClick={() => setMaterialVisible(true)} text={lastMaterial}/>
            <Link><GraphSquare text={"TODO"}/></Link>
        </div>
    )
}