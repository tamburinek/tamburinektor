import styles from "./TestItemAll.module.scss"

export const TestItemAll = (props) => {
    return(
        <div className={styles.item}>
            <p>{props.item}</p>
            <div className={styles.buttons}>
                <button onClick={() => window.location = "test/creation?id=" + props.id} className={styles.edit}>Upravit</button>
            </div>
        </div>
    )
}