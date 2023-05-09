import styles from './AllClassLectureItem.module.scss'
import {Link} from "react-router-dom";
import close from "../../../../assets/png/close.png"
import play from "../../../../assets/png/play.png";


export const AllClassLectureItem = (props) => {

    return(
        <div className={styles.main}>
            <span className={styles.text}>{props.description}</span>
            <div className={styles.buttons}>
                {props.type === "lection" && <img onClick={() => window.location = "lecture?id=" + props.id} className={styles.play} src={play} alt={"play"}/>}
                <img onClick={props.click} className={styles.plus} src={close} alt={"close"}/>
            </div>
        </div>
    )
}