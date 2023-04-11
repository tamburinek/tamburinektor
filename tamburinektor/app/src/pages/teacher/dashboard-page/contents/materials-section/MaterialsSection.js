import styles from './MaterialsSection.module.scss'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {CreateSquare} from "../../../../../parts/squares/create-square/CreateSquare";
import {AllSquare} from "../../../../../parts/squares/all-square/AllSquare";
import {CountSquare} from "../../../../../parts/squares/count-square/CountSquare";
import {LastSquare} from "../../../../../parts/squares/last-square/LastSquare";
import {GraphSquare} from "../../../../../parts/squares/graph-square/GraphSquare";

export const MaterialsSection = () => {

    return (
        <div className={styles.main}>
            <CreateSquare text={"Vytvořit materiál"}/>
            <AllSquare text={"Zobrazit vše"}/>
            <Link><CountSquare text={"176 materiálů"}/></Link>
            <Link><LastSquare text={"TODO"}/></Link>
            <Link><GraphSquare text={"TODO"}/></Link>
        </div>
    )
}