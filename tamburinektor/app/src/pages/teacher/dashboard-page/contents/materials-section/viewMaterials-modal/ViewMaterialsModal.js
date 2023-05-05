import styles from './ViewMaterialsModal.module.scss'
import {useEffect, useState} from "react";
import {BoxItem} from "../../../../lecture-create/box-item/BoxItem";
import MaterialsListApi from "../../../../../../services/materialsListApi";

export const ViewMaterialsModal = (props) => {

    const [activeName, setActiveName] = useState("definition");
    const activeDiv = styles.categoryItem + " " + styles.active
    const nonActiveDiv = styles.categoryItem


    const [definitions, setDefinitions] = useState([])
    const [images, setImages] = useState([])
    const [questions, setQuestions] = useState([])
    const [tasks, setTasks] = useState([])
    const [quizes, setQuizes] = useState([])

    let listDefinitions = definitions.map(defin => {
        return (<BoxItem key={defin.id} type={activeName} edit={() => editMaterial()} item={defin.description}/>)
    })

    let listImages = images.map(img => {
        return (<BoxItem key={img.id} type={activeName} edit={() => editMaterial()} item={img.description}/>)
    })

    let listQuestions = questions.map(quest => {
        return (<BoxItem key={quest.id} type={activeName} edit={() => editMaterial()} item={quest.questionText}/>)
    })

    let listTasks = tasks.map(task => {
        return (<BoxItem key={task.id} type={activeName} edit={() => editMaterial()} item={task.question}/>)
    })

    let listQuizes = quizes.map(quiz => {
        return (<BoxItem key={quiz.id} type={activeName} edit={() => editMaterial()} item={quiz.name}/>)
    })


    const fetchDef = () => {
        MaterialsListApi.getAllDefinitions().then(response => {
            setDefinitions(response.data)
        })
    }

    const fetchImages = () => {
        MaterialsListApi.getAllImages().then(response => {
            setImages(response.data)
        })
    }

    const fetchQuestions = () => {
        MaterialsListApi.getAllQuestions().then(response => {
            setQuestions(response.data)
        })
    }

    const fetchTasks = () => {
        MaterialsListApi.getAllTasks().then(response => {
            setTasks(response.data)
        })
    }

    const fetchQuizes = () => {
        MaterialsListApi.getAllQuizes().then(response => {
            setQuizes(response.data)
        })
    }

    let editMaterial = () => {
        props.editMaterial()
    }

    let changeType = (type) => {
        setActiveName(type)
        props.onChange(type)
    }

    useEffect(() => {
        fetchDef()
        fetchImages()
        fetchQuestions()
        fetchTasks()
        //fetchQuizes()
    }, [])

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
                    {activeName === "definition" && definitions.length > 1 && listDefinitions}
                    {activeName === "image" && images.length > 1 && listImages}
                    {activeName === "question" && questions.length > 1 && listQuestions}
                    {activeName === "task" && tasks.length > 1 && listTasks}
                    {activeName === "quiz" && quizes.length > 1 && listQuizes}
                </div>
            </div>
        </div>
    )
}