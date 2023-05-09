import styles from './ClassRoomPage.module.scss'
import logo from "../../../assets/png/logo.png";

import {useEffect, useState} from "react";
import ClassRoomApi from "../../../services/classRoomApi";
import LectureApi from "../../../services/lectureApi";
import {AllLectureItem} from "./allLectures-item/AllLectureItem";
import {AllClassLectureItem} from "./allClassLectures-item/AllClassLectureItem";
import ClassRoomPageApi from "../../../services/classRoomPageApi";
import TestApi from "../../../services/testApi";


export const ClassRoomPage = () => {

    const [classes, setClasses] = useState([])
    const [rerender, setRerender] = useState(false)
    const [activeClass, setActiveClass] = useState(0)

    const activeDiv = styles.classItem + " " + styles.active
    const nonActiveDiv = styles.classItem

    const [lecturesOfClass, setLectureClass] = useState([])
    const [testsOfClass, setTestsOfClass] = useState([])
    const [studentsOfClass, setStudents] = useState([])

    let listClasses = classes.map((classItem) => {
        return <div key={classItem.id} id={classItem.id} onClick={() => {
                changeActive(classItem.id)
        }} className={activeClass === classItem.id ? activeDiv : nonActiveDiv}> {classItem.name} </div>
    })

    const [lecturesAll, setLecturesAll] = useState([])
    let listAllLectures = lecturesAll.map(item => {
        return <AllLectureItem key={item.id} id={item.id} type={"lection"} description={item.description}
                               lectures={lecturesOfClass} click={() => addToClassLecture(item.id, item.description)}/>;
    })

    const [testsAll, setTestsAll] = useState([])
    let listAllTests = testsAll.map(item => {
        return <AllLectureItem key={item.id} id={item.id} description={item.description}
                               lectures={lecturesOfClass} click={() => addToClassTest(item.id, item.description)}/>;
    })

    const classContainsLecture = (id) => {
        for (const item of lecturesOfClass) {
            if (item.id === id){
                return true
            }
        }
        return false
    }

    const classContainsTest = (id) => {
        for (const item of testsOfClass) {
            if (item.id === id){
                return true
            }
        }
        return false
    }

    let changeActive = (id) => {
        setActiveClass(id)
        setLectureClass([])
        setTestsOfClass([])
        ClassRoomPageApi.getAllLecturesOfClass(id).then((res) => {
            let helper = []
            for (const item of res.data) {
                helper.push({id:item.id, description:item.description})
            }
            setLectureClass(helper)
        })
        ClassRoomPageApi.getAllTestsOfClass(id).then((res) => {
            let helper = []
            for (const item of res.data) {
                helper.push({id:item.id, description:item.description})
            }
            setTestsOfClass(helper)
        })
        ClassRoomApi.getAllStudentsOfClass(id).then((res) => {
            setStudents(res.data)
        })
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    }

    let addToClassLecture = (id, description) => {
        if (classContainsLecture(id) || activeClass === 0){
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

    let addToClassTest = (id, description) => {
        if (classContainsTest(id) || activeClass === 0){
            return
        }
        let helper = testsOfClass
        helper.push({id:id, description:description})
        ClassRoomPageApi.addTestToClass(activeClass, id).then(res => {
            console.log(res)
            setTestsOfClass(helper)
        })
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    }


    let listClassLectures = lecturesOfClass.map(item => {
        return <AllClassLectureItem key={item.id} id={item.id} type={"lection"} description={item.description}
                               click={() => removeFromClassLecture(item.id)}/>;
    })

    let listTestClasses = testsOfClass.map(item => {
        return <AllClassLectureItem key={item.id} id={item.id} description={item.description}
                                    click={() => removeFromClassTest(item.id)}/>;
    })

    let listUsers = studentsOfClass.map(item => {
        return <div className={styles.user}>{item.firstName + " " + item.lastName}</div>;
    })

    let removeFromClassLecture = (id) => {
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

    let removeFromClassTest = (id) => {
        ClassRoomPageApi.removeTestFromCLass(activeClass, id).then(res => {
            console.log(res)
            let helper = []
            for (const item of testsOfClass) {
                if (item.id !== id){
                    helper.push({id:item.id, description:item.description})
                }
            }
            setTestsOfClass(helper)
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
        TestApi.getAllTests().then(res => {
            setTestsAll(res.data)
        })
    },[])

    return(
        <div className={styles.main}>
            <div className={styles.test}>
                <img onClick={() => window.location = "/dashboard"} className={styles.logo} alt={"logo"} src={logo}/>
                <div className={styles.classes}>
                    <p className={styles.className}>Vybrat třídu</p>
                    <div className={styles.classList}>
                        {listClasses}
                    </div>
                </div>
                <div className={styles.right}>
                    <div className={styles.users}>
                        <p className={styles.className}>Uživatelé</p>
                        <div className={styles.usersList}>
                            {listUsers}
                        </div>
                    </div>
                    <div className={styles.rightFromUsers}>
                        <div className={styles.lectures}>
                            <div className={styles.classLectures}>
                                <p className={styles.className}>Lekce, které vidí studenti</p>
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
                        <div className={styles.lectures}>
                            <div className={styles.classLectures}>
                                <p className={styles.className}>Testy, které vidí studenti</p>
                                <div className={styles.classLecturesItems} autoFocus={rerender}>
                                    {listTestClasses}
                                </div>
                            </div>
                            <div className={styles.allLectures}>
                                <p className={styles.className}>Všechny testy</p>
                                <div className={styles.classLecturesItems}>
                                    {activeClass !== 0 && listAllTests}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}