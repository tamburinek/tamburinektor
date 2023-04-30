import styles from "./TestItem.module.scss";
import close from "../../../../assets/png/close.png";

export const TestItem = (props) => {
    return(
        <div className={styles.itemList}>
            <div className={styles.dot}>
                U
            </div>
            <p className={styles.description}>{props.item}</p>
            <input className={styles.mark} type={"number"} min={1} max={10} step={1} defaultValue={1}/>
            <img className={styles.close} src={close} alt={"close"}/>
        </div>
    )
}