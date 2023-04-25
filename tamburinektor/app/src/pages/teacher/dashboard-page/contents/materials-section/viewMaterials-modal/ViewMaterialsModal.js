import styles from './ViewMaterialsModal.module.scss'

import {useEffect, useState} from "react";
import {BoxItem} from "../../../../lecture-create/box-item/BoxItem";

export const ViewMaterialsModal = (props) => {

    const [activeName, setActiveName] = useState("definice");
    const activeDiv = styles.categoryItem + " " + styles.active
    const nonActiveDiv = styles.categoryItem

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <BoxItem item={item}/>
    );

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.box} onClick={event => event.stopPropagation()}>
                <div className={styles.category}>
                    <div className={activeName === "definice" ? activeDiv : nonActiveDiv}
                         onClick={() => setActiveName("definice")}>
                        Definice
                    </div>
                    <div className={activeName === "grafy" ? activeDiv : nonActiveDiv}
                         onClick={() => setActiveName("grafy")}>
                        Grafy
                    </div>
                    <div className={activeName === "obrazky" ? activeDiv : nonActiveDiv}
                         onClick={() => setActiveName("obrazky")}>
                        Obrázky
                    </div>
                    <div className={activeName === "otazky" ? activeDiv : nonActiveDiv}
                         onClick={() => setActiveName("otazky")}>
                        Otázky
                    </div>
                    <div className={activeName === "ukoly" ? activeDiv : nonActiveDiv}
                         onClick={() => setActiveName("ukoly")}>
                        Úkoly
                    </div>
                    <div className={activeName === "quiz" ? activeDiv : nonActiveDiv}
                         onClick={() => setActiveName("quiz")}>
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