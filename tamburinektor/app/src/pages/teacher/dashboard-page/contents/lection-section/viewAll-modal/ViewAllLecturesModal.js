import styles from './ViewAllLecturesModal.module.scss'

import {useEffect, useState} from "react";
import closeImage from "../../../../../../assets/png/close.png";
import {BoxItem} from "../../../../lecture-create/box-item/BoxItem";
import {LecturesItem} from "./lectures-item/LecturesItem";

export const ViewAllLecturesModal = (props) => {

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <LecturesItem item={item}/>
    );

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.box} onClick={event => event.stopPropagation()}>
                <div className={styles.filter}>
                    <input className={styles.search} type={"text"} placeholder={"Vyhledat...."}/>
                    <select className={styles.filterName}>
                        <option>Nejnovější</option>
                        <option>Nejstarší</option>
                        <option>Abecedně vzestupně</option>
                        <option>Abecedně sestupně</option>
                    </select>
                    <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                </div>
                <div className={styles.lectures}>
                    {listBoxItems}
                </div>
            </div>
        </div>
    )
}