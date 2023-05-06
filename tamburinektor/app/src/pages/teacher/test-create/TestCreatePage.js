import styles from './TestCreatePage.module.scss'
import {useEffect, useState} from "react";
import {CreateQuestion} from "../dashboard-page/contents/test-section/createQuestion-modal/CreateQuestion";
import {Link} from "react-router-dom";
import logo from "../../../assets/png/logo.png";
import {BoxItem} from "../lecture-create/box-item/BoxItem";
import {TestItem} from "./test-item/TestItem";
import MaterialsListApi from "../../../services/materialsListApi";
import TestApi from "../../../services/testApi";

export const TestCreatePage = () => {

    const [createModalVisible, setCreateVisible] = useState(false)
    const [activeCategory, setActiveCategory] = useState("closed")

    const activeDiv = styles.category + " " + styles.active
    const nonActiveDiv = styles.category

    let createQuestion = (event) => {
        event.preventDefault()
        setCreateVisible(true)
    }

    const [closedQuestions, setClosedQuestions] = useState([])
    const [openQuestions, setOpenQuestions] = useState([])

    const [testQuestions, setTestQuestions] = useState([])
    const [rerender, setRerender] = useState(false)

    const [questionId, setQuestionId] = useState(undefined)


    const listTestItems = testQuestions.map((item) =>
        <TestItem key={item.id} item={item.text} category={item.activeName} remove={() => removeItem(item.id)}/>
    );

    let listClosed = closedQuestions.map(quest => {
        return (<BoxItem key={quest.id} type={activeCategory}
                         add={() => addItem(quest.id, activeCategory, quest.question)}
                         edit={() => editMaterial(quest.id)} item={quest.question}/>)
    })

    let listOpen = openQuestions.map(quest => {
        return (<BoxItem key={quest.id} type={activeCategory}
                         add={() => addItem(quest.id, activeCategory, quest.question)}
                         edit={() => editMaterial(quest.id)} item={quest.question}/>)
    })

    let contain = (id) => {
        for (const item of testQuestions) {
            if (item.id === id){
                return true;
            }
        }
        return false;
    }

    let addItem = (id, activeName, text) => {
        if (contain(id)){
            return
        }
        let helper = testQuestions
        helper.push({id:id, activeName:activeName, text:text})
        setTestQuestions(helper)
        if (rerender === true){
            setRerender(false)
        }else {
            setRerender(true)
        }
    }

    let editMaterial = (id) => {

    }

    let removeItem = (id) => {
        let helper = []
        for (const item of testQuestions) {
            if (item.id !== id){
                helper.push(item)
            }
        }
        setTestQuestions(helper)
        if (rerender === true){
            setRerender(false)
        }else {
            setRerender(true)
        }
    }

    const fetchClosed = () => {
        TestApi.getAllClose().then(response => {
            setClosedQuestions(response.data)
        })
    }

    const fetchOpen = () => {
        TestApi.getAllOpen().then(response => {
            setOpenQuestions(response.data)
        })
    }

    useEffect(() => {
        if (createModalVisible === true){
            return
        }
        fetchClosed()
        fetchOpen()
    }, [createModalVisible])

    return (
        <div className={styles.main}>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <div className={styles.left}>
                <div className={styles.box}>
                    <div className={styles.categories}>
                        <div className={activeCategory === "closed" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveCategory("closed")}>
                            Uzavřené otázky
                        </div>
                        <div className={activeCategory === "open" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveCategory("open")}>
                            Otevřené otázky
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
                        {activeCategory === "closed" && closedQuestions.length > 1 && listClosed}
                        {activeCategory === "open" && openQuestions.length > 1 && listOpen}
                    </div>
                </div>
                <button className={styles.add} onClick={(event) => createQuestion(event)}>
                    Vytvořit otázku</button>
            </div>
            <div className={styles.right}>
                <label className={styles.label}>Popis testu</label>
                <input className={styles.description} placeholder={'Lineární rovnice 4.A.'} type={"text"}/>
                <div className={styles.paper} autoFocus={rerender}>
                    {listTestItems}
                </div>
                <button className={styles.create}>Vytvořit test</button>
            </div>

            {createModalVisible === true && <CreateQuestion id={questionId} onClose={() => {
                setCreateVisible(false)
                setQuestionId(undefined)
            }}/>}
        </div>
    )
}