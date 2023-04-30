import styles from "./BoxItem.module.scss";
import pencil from "../../../../assets/png/pencil-grey.png";

export const BoxItem = (props) => {

    return(
    <div className={styles.itemList}>
        <p className={styles.description}>{props.item}</p>
        {props.type !== "graph" &&
            <div className={styles.images}>
                <img onClick={props.edit} className={styles.pencil} src={pencil} alt={"pencil"}/>
            </div>
        }
    </div>
    )
}