import styles from './LectureCreatePage.module.scss'
import {Link, useLocation} from "react-router-dom";
import logo from "../../../assets/png/logo.png";

import {useEffect, useState} from "react";
import {
    CreateMaterialModal
} from "../dashboard-page/contents/materials-section/createMaterials-modal/CreateMaterialModal";
import {BoxItem} from "./box-item/BoxItem";
import {PaperItem} from "./paper-item/PaperItem";
import {EditMaterialModal} from "../dashboard-page/contents/materials-section/editMaterial-modal/EditMaterialModal";
import MaterialsListApi from "../../../services/materialsListApi";
import LectureApi from "../../../services/lectureApi";


export const LectureCreatePage = () => {

    //methods for location
    const location = useLocation();
    const params = new URLSearchParams(location.search)
    const [createModal, setCreateModal] = useState(false);
    const [activeName, setActiveName] = useState("definition");
    const [editMaterialVisible, setMaterialVisible] = useState(false)
    const [materialIndex, setMaterialIndex] = useState(undefined)
    const activeDiv = styles.categoryItem + " " + styles.active
    const nonActiveDiv = styles.categoryItem
    const [definitions, setDefinitions] = useState([])
    const [images, setImages] = useState([])
    const [questions, setQuestions] = useState([])
    const [tasks, setTasks] = useState([])
    const [quizes, setQuizes] = useState([])

    let listDefinitions = definitions.map(defin => {
        return (<BoxItem key={defin.id} type={activeName} add={() => addItem(defin.id, activeName, defin.description)} edit={() => editMaterial(defin.id)} item={defin.description}/>)
    })

    let listImages = images.map(img => {
        return (<BoxItem key={img.id} type={activeName} add={() => addItem(img.id, activeName, img.description)} edit={() => editMaterial(img.id)} item={img.description}/>)
    })

    let listQuestions = questions.map(quest => {
        return (<BoxItem key={quest.id} type={activeName} add={() => addItem(quest.id, activeName, quest.questionText)} edit={() => editMaterial(quest.id)} item={quest.questionText}/>)
    })

    let listTasks = tasks.map(task => {
        return (<BoxItem key={task.id} type={activeName} add={() => addItem(task.id, activeName, task.question)} edit={() => editMaterial(task.id)} item={task.question}/>)
    })

    let listQuizes = quizes.map(quiz => {
        return (<BoxItem key={quiz.id} type={activeName} add={() => addItem(quiz.id, activeName, quiz.name)} edit={() => editMaterial(quiz.id)} item={quiz.name}/>)
    })

    const fetchMat = () => {
        fetchDef()
    }

    const fetchDef = () => {
        MaterialsListApi.getAllDefinitions().then(response => {
            setDefinitions(response.data)
        }).then(fetchImages)
    }

    const fetchImages = () => {
        MaterialsListApi.getAllImages().then(response => {
            setImages(response.data)
        }).then(fetchQuestions)
    }

    const fetchQuestions = () => {
        MaterialsListApi.getAllQuestions().then(response => {
            setQuestions(response.data)
        }).then(fetchTasks)
    }

    const fetchTasks = () => {
        MaterialsListApi.getAllTasks().then(response => {
            setTasks(response.data)
        }).then(fetchQuizes)
    }

    const fetchQuizes = () => {
        MaterialsListApi.getAllQuizes().then(response => {
            setQuizes(response.data)
            setFetched(true)
        })
    }

    let editMaterial = (id) => {
        setMaterialIndex(id)
        setMaterialVisible(true)
    }

    let contain = (id) => {
        for (const item of paperItems) {
            if (item.id === id){
                return true
            }
        }
        return false
    }

    let addItem = (id, type, text) => {
        if (contain(id)){
            return
        }
        let helper = paperItems
        helper.push({id:id, lectureType:type, name:text})
        setPaperItems(helper)
        if (rerender === true){
            setrerender(false)
        } else {
            setrerender(true)
        }
    }

    let removeItem = (id) => {
        let helper = []
        for (const item of paperItems) {
            if (item.id !== id){
                helper.push(item)
            }
        }
        setPaperItems(helper)
        if (rerender === true){
            setrerender(false)
        } else {
            setrerender(true)
        }
    }

    const [paperItems, setPaperItems] = useState([])
    const [rerender, setrerender] = useState(true)

    const listPaperItems = paperItems.map((item) =>
        <PaperItem key={item.id} remove={() => removeItem(item.id)} type={item.lectureType} item={item.name}/>
    );

    let addLectureItem = (id, type) => {
        if (type === "definition"){
            for (const definition of definitions) {
                if (definition.id === id){
                    addItem(id, type, definition.description)
                }
            }
        } else if (type === "image"){
            for (const image of images) {
                if (image.id === id){
                    addItem(id, type, image.description)
                }
            }
        } else if (type === "question"){
            for (const question of questions) {
                if (question.id === id){
                    addItem(id, type, question.questionText)
                }
            }
        } else if (type === "task"){
            for (const task of tasks) {
                if (task.id === id){
                    addItem(id, type, task.question)
                }
            }
        } else if (type === "quiz"){
            for (const quiz of quizes) {
                if (quiz.id === id){
                    addItem(id, type, quiz.name)
                }
            }
        }
        if (rerender === true){
            setrerender(false)
        } else {
            setrerender(true)
        }
    }

    let setLecture = (id) => {
        LectureApi.getLectureById(id).then((res) => {
            setLectureDescription(res.data.description)
            for (const item of res.data.lectureEntities) {
                addLectureItem(item.id, item.lectureType)
            }
        })
    }

    const [fetched, setFetched] = useState(false)

    useEffect(() => {
        if (createModal === true || editMaterialVisible === true){
            return
        }
        fetchMat()
    }, [createModal, editMaterialVisible])

    useEffect(() => {
        if (fetched === false){
            return
        }
        if (params.get("id") !== null){
            setButtonText("Aktualizovat")
            setLecture(params.get("id"))
        }
    }, [fetched])

    let confirm = (e) => {
        e.preventDefault()
        if (lectureDescription.length < 1){
            return
        }
        if (params.get("id") !== null){
            LectureApi.updateLecture(params.get("id"), lectureDescription, paperItems).then(() => {
                window.location = "/dashboard"
            })
        } else {
            LectureApi.createLecture(lectureDescription, paperItems).then((res) => {
                setLectureDescription("")
                setPaperItems([])
            })
        }
    }

    const [lectureDescription, setLectureDescription] = useState("")
    const [buttonText, setButtonText] = useState("Vytvořit lekci")

    return (
        <div className={styles.main}>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <div className={styles.left}>
                <div className={styles.box}>
                    <div className={styles.category}>
                        <div className={activeName === "definition" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("definition")}>
                            Definice
                        </div>
                        <div className={activeName === "graph" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("graph")}>
                            Grafy
                        </div>
                        <div className={activeName === "image" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("image")}>
                            Obrázky
                        </div>
                        <div className={activeName === "question" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("question")}>
                            Otázky
                        </div>
                        <div className={activeName === "task" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("task")}>
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
                        {activeName === "definition" && definitions.length > 0 && listDefinitions}
                        {activeName === "image" && images.length > 0 && listImages}
                        {activeName === "question" && questions.length > 0 && listQuestions}
                        {activeName === "task" && tasks.length > 0 && listTasks}
                        {activeName === "quiz" && quizes.length > 0 && listQuizes}
                    </div>
                </div>
                <button className={styles.add} onClick={() => setCreateModal(true)}>Vytvořit materiál</button>
                {createModal && <CreateMaterialModal onClose={() => setCreateModal(false)}/>}

                {editMaterialVisible === true && <EditMaterialModal id={materialIndex} type={activeName} onClose={() => {
                    setMaterialVisible(false)
                }}/>}
            </div>



            <div className={styles.right}>
                <label className={styles.label}>Popis lekce</label>
                <input onChange={e => {
                    setLectureDescription(e.target.value)}}
                       value={lectureDescription} className={styles.description}
                       placeholder={'Lineární rovnice 4.A.'} type={"text"}/>
                <div className={styles.paper} autoFocus={rerender}>
                    {listPaperItems}
                </div>
                <button onClick={confirm} className={styles.create}>{buttonText}</button>
                {editMaterialVisible === true && <EditMaterialModal id={materialIndex} type={activeName} onClose={() => setMaterialVisible(false)}/>}
            </div>
        </div>
    )
}