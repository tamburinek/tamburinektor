import styles from "./PaperItem.module.scss";
import close from "../../../../assets/png/close.png";

export const PaperItem = (props) => {
    return(
        <div className={styles.itemList}>
            <div className={styles.dot}>
                D
            </div>
            <p className={styles.description}>{props.item}</p>
            <img className={styles.close} src={close} alt={"eye"}/>
        </div>
    )
}