import styles from "./PaperItem.module.scss";
import close from "../../../../assets/png/close.png";

export const PaperItem = (props) => {

    return(
        <div className={styles.itemList}>
            {props.type === "definition" && <div className={styles.green}>
                D
            </div>}
            {props.type === "image" && <div className={styles.red}>
                I
            </div>}
            {props.type === "question" && <div className={styles.blue}>
                O
            </div>}
            {props.type === "graph" && <div className={styles.brown}>
                G
            </div>}
            {props.type === "task" && <div className={styles.white}>
                Ú
            </div>}
            {props.type === "quiz" && <div className={styles.purple}>
                Q
            </div>}
            <p className={styles.description}>{props.item}</p>
            <img onClick={props.remove} className={styles.close} src={close} alt={"close"}/>
        </div>
    )
}