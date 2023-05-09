import styles from './ViewAllLecturesModal.module.scss'

import {useEffect, useState} from "react";
import closeImage from "../../../../../../assets/png/close.png";
import {LecturesItem} from "./lectures-item/LecturesItem";
import LectureApi from "../../../../../../services/lectureApi";

export const ViewAllLecturesModal = (props) => {

    const [boxItems, setBoxItems] = useState([])
    const listBoxItems = boxItems.map((item) =>
        <LecturesItem key={item.id} id={item.id} item={item.description}/>
    );

    useEffect(() => {
        LectureApi.getAllLectures().then((res) => {
            setBoxItems(res.data)
        })
    },[])

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