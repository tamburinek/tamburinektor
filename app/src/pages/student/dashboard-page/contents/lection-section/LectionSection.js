import styles from "./LectionSection.module.scss"
import {LectionItem} from "./lection-item/LectionItem";
import {useEffect, useState} from "react";
import ClassRoomApi from "../../../../../services/classRoomApi";
import ClassRoomPageApi from "../../../../../services/classRoomPageApi";

export const LectionSection = (props) => {

    const [activeClass, setActiveClass] = useState(-1)
    const [classes, setClasses] = useState([])

    const [lectures, setLectures] = useState([])
    const [rerender, setRerender] = useState(false)

    const listBoxItems = lectures.map((item) =>
        <LectionItem key={item.id} id={item.id} item={item.description}/>
    );

    const listClasses = classes.map((item) =>
        <option key={item.id} value={item.id}>{item.name}</option>
    );

    useEffect(() => {
        ClassRoomApi.getMyClasses().then((res) => {
            setClasses(res.data)
        }).then(() => {
            if (activeClass === -1) {
                setLectures([])
            } else {
                ClassRoomPageApi.getAllLecturesOfClass(activeClass).then(res => {
                    setLectures(res.data)
                })
            }
            if (rerender === true){
                setRerender(false)
            } else {
                setRerender(true)
            }
        })
    }, [])

    useEffect(() => {
        if (activeClass === -1) {
            return
        }
        ClassRoomPageApi.getAllLecturesOfClass(activeClass).then(res => {
            setLectures(res.data)
        })
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    },[activeClass])


    return(
        <div className={styles.main}>
            <select className={styles.select} onChange={(e) => setActiveClass(e.target.value)}
                    defaultValue={-1}>
                <option disabled value={-1}> -- vyber třídu -- </option>
                {listClasses}
            </select>
            <div className={styles.filter}>
                <input className={styles.search} type={"text"} placeholder={"Vyhledat...."}/>
                <select className={styles.filterName}>
                    <option>Nejnovější</option>
                    <option>Nejstarší</option>
                    <option>Abecedně vzestupně</option>
                    <option>Abecedně sestupně</option>
                </select>
            </div>
            <div className={styles.menu}>
                <div className={styles.items}>
                    {listBoxItems}
                </div>
            </div>
        </div>
    )
}