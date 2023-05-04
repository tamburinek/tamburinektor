import styles from './SideSection.module.scss'
import {useEffect, useState} from "react";

import file from "../../../../assets/png/dashboard/file.png"
import test from "../../../../assets/png/dashboard/test.png"
import logout from "../../../../assets/png/dashboard/logout.png"
import classRoom from "../../../../assets/png/dashboard/room.png"
import AuthService from "../../../../services/auth.service";
import {LectionSection} from "../contents/lection-section/LectionSection";
import {TestSection} from "../contents/test-section/TestSection";
import {ClassRoomSection} from "../contents/classroom-section/ClassRoomSection";


export const SideSection = () => {

    const [activeName, setActiveName] = useState("lekce");
    const activeDiv = styles.item + " " + styles.active
    const nonActiveDiv = styles.item


    return (
        <div className={styles.main}>
            <div className={styles.square}/>
            <div className={styles.side}>
                <div className={activeName === "lekce" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("lekce")}>
                    <img src={file} alt={"file"}/>
                    <p>Lekce</p>
                </div>
                <div className={activeName === "testy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("testy")}>
                    <img src={test} alt={"file"}/>
                    <p>Testy</p>
                </div>
                <div className={activeName === "tridy" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("tridy")}>
                    <img src={classRoom} alt={"classRoom"}/>
                    <p>Třídy</p>
                </div>
                <div onClick={() => AuthService.logout()} className={styles.item + " " + styles.last} >
                    <img src={logout} alt={"file"}/>
                    <p>Odhlásit</p>
                </div>
            </div>
            <div className={styles.right}>
                {activeName === "lekce" && <LectionSection/>}
                {activeName === "testy" && <TestSection/>}
                {activeName === "tridy" && <ClassRoomSection/>}
            </div>
        </div>
    )
}