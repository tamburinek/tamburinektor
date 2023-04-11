import styles from './SideSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";

import logo from "../../../../assets/png/logo.png";
import file from "../../../../assets/png/dashboard/file.png"
import test from "../../../../assets/png/dashboard/test.png"
import user from "../../../../assets/png/dashboard/user.png"
import material from "../../../../assets/png/dashboard/material.png"
import stats from "../../../../assets/png/dashboard/stats.png"
import room from "../../../../assets/png/dashboard/room.png"
import logout from "../../../../assets/png/dashboard/logout.png"
import {TestSection} from "../contents/test-section/TestSection";
import {LectionSection} from "../contents/lection-section/LectionSection";
import {MaterialsSection} from "../contents/materials-section/MaterialsSection";
import {StudentsSection} from "../contents/students-section/StudentsSection";
import {ClassroomSection} from "../contents/classroom-section/ClassroomSection";
import {StatisticsSection} from "../contents/statistics-section/StatisticsSection";

export const SideSection = () => {

    const [activeName, setActiveName] = useState("testy");
    const activeDiv = styles.item + " " + styles.active
    const nonActiveDiv = styles.item


    //todo change name depend on user
    return (
        <div className={styles.main}>
            <Link to={'/'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <div className={styles.square}/>
            <div className={styles.side}>

                <div className={activeName === "testy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("testy")}>
                    <img src={test} alt={"file"}/>
                    <p>Testy</p>
                </div>
                <div className={activeName === "lekce" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("lekce")}>
                    <img src={file} alt={"file"}/>
                    <p>Lekce</p>
                </div>
                <div className={activeName === "materialy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("materialy")}>
                    <img src={material} alt={"file"}/>
                    <p>Materiály</p>
                </div>
                <div className={activeName === "tridy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("tridy")}>
                    <img src={room} alt={"file"}/>
                    <p>Třídy</p>
                </div>
                <div className={activeName === "studenti" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("studenti")}>
                    <img src={user} alt={"file"}/>
                    <p>Studenti</p>
                </div>
                <div className={activeName === "statistiky" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("statistiky")}>
                    <img src={stats} alt={"file"}/>
                    <p>Statistiky</p>
                </div>
                <div className={styles.item + " " + styles.last} >
                    <img src={logout} alt={"file"}/>
                    <p>Odhlásit</p>
                </div>
            </div>
            <div className={styles.right}>
                <span className={styles.name}>Arťom Ňorba</span>
                {activeName === "testy" && <TestSection text={"Novy text"}/>}
                {activeName === "lekce" && <LectionSection/>}
                {activeName === "materialy" && <MaterialsSection/>}
                {activeName === "tridy" && <ClassroomSection/>}
                {activeName === "studenti" && <StudentsSection/>}
                {activeName === "statistiky" && <StatisticsSection/>}
            </div>
        </div>
    )
}