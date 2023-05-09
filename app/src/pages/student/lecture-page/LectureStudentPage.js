import styles from './LectureStudentPage.module.scss'
import {useEffect, useState} from "react";
import {Link, useLocation} from "react-router-dom";
import logo from "../../../assets/png/logo.png";

export const LectureStudentPage = () => {

    return (
        <div className={styles.main}>
            <Link to={'/dashboard'}><img className={styles.logo} src={logo} alt={'logo'}/> </Link>
                Student
        </div>
    )
}