import styles from './SideSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";

import logo from "../../../../assets/png/logo.png";
import file from "../../../../assets/png/dashboard/file.png"
import test from "../../../../assets/png/dashboard/test.png"
import material from "../../../../assets/png/dashboard/material.png"
import room from "../../../../assets/png/dashboard/room.png"
import logout from "../../../../assets/png/dashboard/logout.png"
import {TestSection} from "../contents/test-section/TestSection";
import {LectionSection} from "../contents/lection-section/LectionSection";
import {MaterialsSection} from "../contents/materials-section/MaterialsSection";
import {ClassroomSection} from "../contents/classroom-section/ClassroomSection";
import authHeader from "../../../../services/auth-header";
import axios from "axios";
import {baseUrl} from "../../../../config/const";
import AuthService from "../../../../services/auth.service";

export const SideSection = () => {

    const [activeName, setActiveName] = useState("lekce");
    const [username, setUsername] = useState("");


    const activeDiv = styles.item + " " + styles.active
    const nonActiveDiv = styles.item

    useEffect(() => {
        AuthService.getCurrentUser().then(response =>{
            console.log(response.data)
            localStorage.setItem("user", response.data.firstName + " " + response.data.lastName)
            setUsername(localStorage.getItem("user"))
        })
    },[]);


    return (
        <div className={styles.main}>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
            <div className={styles.square}/>
            <div className={styles.side}>
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
                <div className={activeName === "testy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("testy")}>
                    <img src={test} alt={"file"}/>
                    <p>Testy</p>
                </div>
                <div className={activeName === "tridy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("tridy")}>
                    <img src={room} alt={"file"}/>
                    <p>Třídy</p>
                </div>
                <div onClick={() => AuthService.logout()} className={styles.item + " " + styles.last} >
                    <img src={logout} alt={"file"}/>
                    <p>Odhlásit</p>
                </div>
            </div>
            <div className={styles.right}>
                <span className={styles.name}>{username}</span>
                {activeName === "testy" && <TestSection/>}
                {activeName === "lekce" && <LectionSection/>}
                {activeName === "materialy" && <MaterialsSection/>}
                {activeName === "tridy" && <ClassroomSection/>}
            </div>
        </div>
    )
}