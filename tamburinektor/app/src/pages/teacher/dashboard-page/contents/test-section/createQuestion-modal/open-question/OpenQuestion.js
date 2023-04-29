import styles from "./OpenQuestion.module.scss"

export const OpenQuestion = () => {
    return(
        <div className={styles.main}>
            <h2>Odpověď</h2>
            <input className={styles.input}/>
        </div>
    )
}