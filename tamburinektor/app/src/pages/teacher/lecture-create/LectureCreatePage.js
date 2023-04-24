import styles from './LectureCreatePage.module.scss'
import {Link} from "react-router-dom";
import logo from "../../../assets/png/logo.png";
import {useEffect, useState} from "react";
import {
    CreateMaterialModal
} from "../dashboard-page/contents/materials-section/createMaterials-modal/CreateMaterialModal";


export const LectureCreatePage = () => {

    const [createModal, setCreateModal] = useState(false);
    const [activeName, setActiveName] = useState("definice");

    const activeDiv = styles.categoryItem + " " + styles.active
    const nonActiveDiv = styles.categoryItem

    return (
        <div className={styles.main}>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <div className={styles.left}>
                <div className={styles.box}>
                    <div className={styles.category}>
                        <div className={activeName === "definice" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("definice")}>
                            Definice
                        </div>
                        <div className={activeName === "grafy" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("grafy")}>
                            Grafy
                        </div>
                        <div className={activeName === "obrazky" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("obrazky")}>
                            Obrázky
                        </div>
                        <div className={activeName === "otazky" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("otazky")}>
                            Otázky
                        </div>
                        <div className={activeName === "ukoly" ? activeDiv : nonActiveDiv}
                             onClick={() => setActiveName("ukoly")}>
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
                        Itemy
                    </div>
                </div>
                <button className={styles.add} onClick={() => setCreateModal(true)}>Vytvořit materiál</button>
                {createModal && <CreateMaterialModal onClose={() => setCreateModal(false)}/>}
            </div>
            <div className={styles.right}>
                <label className={styles.label}>Popis lekce</label>
                <input className={styles.description} placeholder={'Lineární rovnice 4.A.'} type={"text"}/>
                <div className={styles.paper}>

                </div>
                <button className={styles.create}>Vytvořit lekci</button>
            </div>
        </div>
    )
}