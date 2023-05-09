import styles from "./LecturesItem.module.scss"

export const LecturesItem = (props) => {

    return(
        <div className={styles.item}>
            <p>{props.item}</p>
            <div className={styles.buttons}>
                <button onClick={() => window.location = "lecture/creation?id=" + props.id} className={styles.edit}>Upravit</button>
                <button onClick={() => window.location = "lecture?id=" + props.id} className={styles.run}>Spustit</button>
            </div>
        </div>
    )
}