import styles from './LectureCreatePage.module.scss'
import {Link} from "react-router-dom";
import logo from "../../../assets/png/logo.png";

import {useEffect, useState} from "react";
import {
    CreateMaterialModal
} from "../dashboard-page/contents/materials-section/createMaterials-modal/CreateMaterialModal";
import {BoxItem} from "./box-item/BoxItem";
import {PaperItem} from "./paper-item/PaperItem";
import {EditMaterialModal} from "../dashboard-page/contents/materials-section/editMaterial-modal/EditMaterialModal";


export const LectureCreatePage = () => {

    const [createModal, setCreateModal] = useState(false);
    const [activeName, setActiveName] = useState("definition");
    const [editMaterialVisible, setMaterialVisible] = useState(false)

    const activeDiv = styles.categoryItem + " " + styles.active
    const nonActiveDiv = styles.categoryItem

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <BoxItem type={activeName} edit={() => setMaterialVisible(true)} item={item}/>
    );

    let paperItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    const listPaperItems = paperItems.map((item) =>
        <PaperItem item={item}/>
    );

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
                        {listBoxItems}
                    </div>
                </div>
                <button className={styles.add} onClick={() => setCreateModal(true)}>Vytvořit materiál</button>
                {createModal && <CreateMaterialModal onClose={() => setCreateModal(false)}/>}
            </div>
            <div className={styles.right}>
                <label className={styles.label}>Popis lekce</label>
                <input className={styles.description} placeholder={'Lineární rovnice 4.A.'} type={"text"}/>
                <div className={styles.paper}>
                    {listPaperItems}
                </div>
                <button className={styles.create}>Vytvořit lekci</button>
                {editMaterialVisible === true && <EditMaterialModal type={activeName} onClose={() => setMaterialVisible(false)}/>}
            </div>
        </div>
    )
}