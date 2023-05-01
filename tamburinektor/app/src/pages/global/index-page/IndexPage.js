import {HeaderSection} from "./header-section/HeaderSection";
import {GraphSection} from "./main-section/graph-section/GraphSection";
import {TextIndexSection} from "./main-section/text-section/TextIndexSection";
import {useEffect, useState} from "react";

import styles from './index.module.scss'

export const IndexPage = () => {

    useEffect(() => {
        if (localStorage.getItem("user")){
            window.location = "/dashboard"
        }
    })

    return (
        <div className={styles.mainContainer}>
            <div className={styles.dot}/>
            <HeaderSection/>
            <div className={styles.downPart}>
                <TextIndexSection/>
                <GraphSection/>
            </div>
        </div>
    )
}