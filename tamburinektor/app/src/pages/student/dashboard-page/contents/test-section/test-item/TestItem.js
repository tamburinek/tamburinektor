import styles from "./TestItem.module.scss"

export const TestItem = (props) => {
    return(
        <div className={styles.lecture}>
            <div className={styles.box}>
                <p>{props.item}</p>
                <button className={styles.button}>Spustit</button>
            </div>
        </div>
    )
}