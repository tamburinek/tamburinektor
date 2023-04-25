import styles from "./BoxItem.module.scss";
import eye from "../../../../assets/png/eye.png";
import pencil from "../../../../assets/png/pencil-grey.png";

export const BoxItem = (props) => {
    return(
    <div className={styles.itemList}>
        <p className={styles.description}>{props.item}</p>
        <div className={styles.images}>
            <img className={styles.eye} src={eye} alt={"eye"}/>
            <img className={styles.pencil} src={pencil} alt={"pencil"}/>
        </div>
    </div>
    )
}