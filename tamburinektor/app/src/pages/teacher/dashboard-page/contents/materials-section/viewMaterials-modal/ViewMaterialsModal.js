import styles from './ViewMaterialsModal.module.scss'

import {useEffect, useState} from "react";
import {BoxItem} from "../../../../lecture-create/box-item/BoxItem";

export const ViewMaterialsModal = (props) => {

    const [activeName, setActiveName] = useState("definition");
    const activeDiv = styles.categoryItem + " " + styles.active
    const nonActiveDiv = styles.categoryItem

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <BoxItem type={activeName} edit={() => editMaterial()} item={item}/>
    );

    let editMaterial = () => {
        props.onClose()
        props.editMaterial()
    }

    let changeType = (type) => {
        setActiveName(type)
        props.onChange(type)
    }

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.box} onClick={event => event.stopPropagation()}>
                <div className={styles.category}>
                    <div className={activeName === "definition" ? activeDiv : nonActiveDiv}
                         onClick={() => changeType("definition")}>
                        Definice
                    </div>
                    <div className={activeName === "graph" ? activeDiv : nonActiveDiv}
                         onClick={() => changeType("graph")}>
                        Grafy
                    </div>
                    <div className={activeName === "image" ? activeDiv : nonActiveDiv}
                         onClick={() => changeType("image")}>
                        Obrázky
                    </div>
                    <div className={activeName === "question" ? activeDiv : nonActiveDiv}
                         onClick={() => changeType("question")}>
                        Otázky
                    </div>
                    <div className={activeName === "task" ? activeDiv : nonActiveDiv}
                         onClick={() => changeType("task")}>
                        Úkoly
                    </div>
                    <div className={activeName === "quiz" ? activeDiv : nonActiveDiv}
                         onClick={() => changeType("quiz")}>
                        Quizy
                    </div>
                </div>
                <div className={styles.filter}>
                    <input className={styles.search} type={"text"} placeholder={"Vyhledat...."}/>
                    <select className={styles.filterName}>
                        <option>Nejnovější</option>
                        <option>Nejstarší</option>
                        <option>Abecedně vzestupně</option>
                        <option>Abecedně sestupně</option>
                    </select>
                </div>
                <div className={styles.items}>
                    {listBoxItems}
                </div>
            </div>
        </div>
    )
}