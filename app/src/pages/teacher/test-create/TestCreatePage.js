import styles from './TestCreatePage.module.scss'
import {useEffect, useState} from "react";
import {CreateQuestion} from "../dashboard-page/contents/test-section/createQuestion-modal/CreateQuestion";
import {Link, useLocation} from "react-router-dom";
import logo from "../../../assets/png/logo.png";
import {BoxItem} from "../lecture-create/box-item/BoxItem";
import {TestItem} from "./test-item/TestItem";
import TestApi from "../../../services/testApi";

export const TestCreatePage = () => {

    const [createModalVisible, setCreateVisible] = useState(false)
    const [activeCategory, setActiveCategory] = useState("closed")
    const [fetched, setFetched] = useState(false)
    const [buttonText, setButtonText] = useState("Vytvořit test")

    const activeDiv = styles.category + " " + styles.active
    const nonActiveDiv = styles.category

    //methods for location
    const location = useLocation();
    const params = new URLSearchParams(location.search)

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
        <TestItem key={item.id} id={item.id} item={item.text} category={item.activeName} remove={() => removeItem(item.id)}/>
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
        setQuestionId(id)
        setCreateVisible(true)
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
        }).then(fetchOpen)
    }

    const fetchOpen = () => {
        TestApi.getAllOpen().then(response => {
            setOpenQuestions(response.data)
            setFetched(true)
        })
    }

    let pushItemToTest = (id) => {
        for (const close of closedQuestions) {
            if (close.id === id){
                addItem(id, "closed", close.question)
            }
        }
        for (const open of openQuestions) {
            if (open.id === id){
                addItem(id, "open", open.question)
            }
        }
    }

    let setTest = (id) => {
        TestApi.getTestById(id).then((res) => {
            setDescription(res.data.description)
            for (const item of res.data.assignments) {
                pushItemToTest(item)
            }
        })
    }

    useEffect(() => {
        if (createModalVisible === true){
            return
        }
        fetchClosed()
    }, [createModalVisible])

    useEffect(() => {
        if (fetched === false){
            return
        }
        if (params.get("id") !== null){
            setButtonText("Aktualizovat")
            setTest(params.get("id"))
        }
    }, [fetched])

    const [description, setDescription] = useState("")

    let confirm = (e) => {
        e.preventDefault()
        if (description.length < 1){
            return
        }
        let helper = testQuestions.map(item => item.id)
        if (params.get("id") !== null){
            TestApi.updateTest(params.get("id"), description, helper).then(() => {
                window.location = "/dashboard"
            })
        } else {
            TestApi.createTest(description, helper).then((res) => {
                setDescription("")
                setTestQuestions([])
            })
        }
    }

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
                        {activeCategory === "closed" && closedQuestions.length > 0 && listClosed}
                        {activeCategory === "open" && openQuestions.length > 0 && listOpen}
                    </div>
                </div>
                <button className={styles.add} onClick={(event) => createQuestion(event)}>
                    Vytvořit otázku</button>
            </div>
            <div className={styles.right}>
                <label className={styles.label}>Popis testu</label>
                <input onChange={(e) => {setDescription(e.target.value)}}
                       value={description} className={styles.description} placeholder={'Lineární rovnice 4.A.'} type={"text"}/>
                <div className={styles.paper} autoFocus={rerender}>
                    {listTestItems}
                </div>
                <button onClick={confirm} className={styles.create}>{buttonText}</button>
            </div>

            {createModalVisible === true && <CreateQuestion id={questionId} onClose={() => {
                setCreateVisible(false)
                setQuestionId(undefined)
            }}/>}
        </div>
    )
}