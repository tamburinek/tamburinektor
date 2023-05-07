import styles from "./TestItem.module.scss";
import close from "../../../../assets/png/close.png";

export const TestItem = (props) => {
    return(
        <div className={styles.itemList}>
            {props.category === "closed" && <div className={styles.green}>
                U
            </div>}
            {props.category === "open" && <div className={styles.blue}>
                O
            </div>}
            <p className={styles.description}>{props.item}</p>
            <img onClick={props.remove} className={styles.close} src={close} alt={"close"}/>
        </div>
    )
}