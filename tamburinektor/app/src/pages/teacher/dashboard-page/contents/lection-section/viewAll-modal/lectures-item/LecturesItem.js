import styles from "./LecturesItem.module.scss"

export const LecturesItem = (props) => {
    return(
        <div className={styles.item}>
            <p>{props.item}</p>
            <div className={styles.buttons}>
                <button onClick={() => window.location = "lecture/creation"} className={styles.edit}>Upravit</button>
                <button className={styles.run}>Spustit</button>
            </div>
        </div>
    )
}