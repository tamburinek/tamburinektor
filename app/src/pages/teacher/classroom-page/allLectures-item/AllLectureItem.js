import styles from './AllLectureItem.module.scss'
import {Link} from "react-router-dom";
import plus from "../../../../assets/png/plus.png"
import play from "../../../../assets/png/play.png"


export const AllLectureItem = (props) => {

    return(
        <div className={styles.main}>
            <span className={styles.text}>{props.description}</span>
            <div className={styles.buttons}>
                {props.type === "lection" && <img onClick={() => window.location = "lecture?id=" + props.id} className={styles.play} src={play} alt={"play"}/>}
                <img onClick={props.click} className={styles.plus} src={plus} alt={"plus"}/>
            </div>
        </div>
    )
}