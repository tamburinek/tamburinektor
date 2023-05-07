import styles from './ClassRoomPage.module.scss'
import logo from "../../../assets/png/logo.png";

import {useEffect, useState} from "react";
import ClassRoomApi from "../../../services/classRoomApi";
import LectureApi from "../../../services/lectureApi";
import {AllLectureItem} from "./allLectures-item/AllLectureItem";
import {AllClassLectureItem} from "./allClassLectures-item/AllClassLectureItem";
import ClassRoomPageApi from "../../../services/classRoomPageApi";


export const ClassRoomPage = () => {

    const [classes, setClasses] = useState([])
    const [rerender, setRerender] = useState(false)
    const [activeClass, setActiveClass] = useState(0)

    const activeDiv = styles.classItem + " " + styles.active
    const nonActiveDiv = styles.classItem

    const [lecturesOfClass, setLectureClass] = useState([])

    let listClasses = classes.map((classItem) => {
        return <div key={classItem.id} id={classItem.id} onClick={() => {
                changeActive(classItem.id)
        }} className={activeClass === classItem.id ? activeDiv : nonActiveDiv}> {classItem.name} </div>
    })

    const [lecturesAll, setLecturesAll] = useState([])
    let listAllLectures = lecturesAll.map(item => {
        return <AllLectureItem key={item.id} id={item.id} description={item.description}
                               lectures={lecturesOfClass} click={() => addToClass(item.id, item.description)}/>;
    })

    const classContains = (id) => {
        for (const item of lecturesOfClass) {
            if (item.id === id){
                return true
            }
        }
        return false
    }

    let changeActive = (id) => {
        setActiveClass(id)
        setLectureClass([])
        ClassRoomPageApi.getAllLecturesOfClass(id).then((res) => {
            let helper = []
            for (const item of res.data) {
                helper.push({id:item.id, description:item.description})
            }
            setLectureClass(helper)
        })
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    }

    let addToClass = (id, description) => {
        if (classContains(id) || activeClass === 0){
            return
        }
        let helper = lecturesOfClass
        helper.push({id:id, description:description})
        ClassRoomPageApi.addLectureToClass(activeClass, id).then(res => {
            console.log(res)
            setLectureClass(helper)
        })
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    }


    let listClassLectures = lecturesOfClass.map(item => {
        return <AllClassLectureItem key={item.id} id={item.id} description={item.description}
                               click={() => removeFromClass(item.id, item.description)}/>;
    })

    let removeFromClass = (id, description) => {
        ClassRoomPageApi.removeLectureFromClass(activeClass, id).then(res => {
            console.log(res)
            let helper = []
            for (const item of lecturesOfClass) {
                if (item.id !== id){
                    helper.push({id:item.id, description:item.description})
                }
            }
            setLectureClass(helper)
            if (rerender === true){
                setRerender(false)
            } else {
                setRerender(true)
            }
        })
    }

    useEffect(() => {
        ClassRoomApi.getAllClassesCreated().then((res) => {
            setClasses(res.data)
        })
        LectureApi.getAllLectures().then(res => {
            setLecturesAll(res.data)
        })
    },[])

    return(
        <div className={styles.main}>
            <div className={styles.test}>
                <img onClick={() => window.location = "/dashboard"} className={styles.logo} alt={"logo"} src={logo}/>
                <div className={styles.classes}>
                    <p className={styles.className}>Třídy</p>
                    <div className={styles.classList}>
                        {listClasses}
                    </div>
                </div>
                <div className={styles.right}>
                    <div className={styles.users}>
                        <p className={styles.className}>Uživatelé</p>
                        <div className={styles.usersList}>

                        </div>
                    </div>
                    <div className={styles.rightFromUsers}>
                        <div className={styles.lectures}>
                            <div className={styles.classLectures}>
                                <p className={styles.className}>Lekce</p>
                                <div className={styles.classLecturesItems} autoFocus={rerender}>
                                    {listClassLectures}
                                </div>
                            </div>
                            <div className={styles.allLectures}>
                                <p className={styles.className}>Všechny lekce</p>
                                <div className={styles.classLecturesItems}>
                                    {activeClass !== 0 && listAllLectures}
                                </div>
                            </div>
                        </div>
                        <div className={styles.tests}>
                            <div className={styles.classTests}>
                                <p>Testy</p>
                            </div>
                            <div className={styles.allTests}>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}