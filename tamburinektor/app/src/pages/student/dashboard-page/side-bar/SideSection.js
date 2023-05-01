import styles from './SideSection.module.scss'
import {useEffect, useState} from "react";

import file from "../../../../assets/png/dashboard/file.png"
import test from "../../../../assets/png/dashboard/test.png"
import stats from "../../../../assets/png/dashboard/stats.png"
import room from "../../../../assets/png/dashboard/room.png"
import logout from "../../../../assets/png/dashboard/logout.png"
import AuthService from "../../../../services/auth.service";

export const SideSection = () => {

    const [activeName, setActiveName] = useState("statistiky");
    const activeDiv = styles.item + " " + styles.active
    const nonActiveDiv = styles.item


    //todo change name depend on user
    return (
        <div className={styles.main}>
            <div className={styles.square}/>
            <div className={styles.side}>
                <div className={activeName === "statistiky" ? activeDiv : nonActiveDiv}
                     onClick={() => setActiveName("statistiky")}>
                    <img src={stats} alt={"file"}/>
                    <p>Statistiky</p>
                </div>
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
            <div className={styles.right}/>
        </div>
    )
}