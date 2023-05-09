import styles from "./TestItem.module.scss"

export const TestItem = (props) => {
    return(
        <div className={styles.lecture}>
            <div className={styles.box}>
                <p>{props.item}</p>
                <button onClick={() => window.location = "/test?id=" + props.id} className={styles.button}>Spustit</button>
            </div>
        </div>
    )
}