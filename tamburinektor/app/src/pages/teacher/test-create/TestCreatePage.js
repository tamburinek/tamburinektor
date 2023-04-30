import styles from './TestCreatePage.module.scss'
import {useEffect, useState} from "react";
import {CreateQuestion} from "../dashboard-page/contents/test-section/createQuestion-modal/CreateQuestion";
import {Link} from "react-router-dom";
import logo from "../../../assets/png/logo.png";
import {BoxItem} from "../lecture-create/box-item/BoxItem";
import {TestItem} from "./test-item/TestItem";

export const TestCreatePage = () => {

    const [createModalVisible, setCreateVisible] = useState(false)
    const [activeCategory, setActiveCategory] = useState("closed")

    const activeDiv = styles.category + " " + styles.active
    const nonActiveDiv = styles.category

    let createQuestion = (event) => {
        event.preventDefault()
        setCreateVisible(true)
    }

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <BoxItem item={item}/>
    );

    let paperItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listTestItems = boxItems.map((item) =>
        <TestItem item={item}/>
    );

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
                        {listBoxItems}
                    </div>
                </div>
                <button className={styles.add} onClick={(event) => createQuestion(event)}>
                    Vytvořit otázku</button>
            </div>
            <div className={styles.right}>
                <label className={styles.label}>Popis testu</label>
                <input className={styles.description} placeholder={'Lineární rovnice 4.A.'} type={"text"}/>
                <div className={styles.paper}>
                    {listTestItems}
                </div>
                <button className={styles.create}>Vytvořit test</button>
            </div>

            {createModalVisible === true && <CreateQuestion onClose={() => setCreateVisible(false)}/>}
        </div>
    )
}