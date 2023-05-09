import styles from './LastSquare.module.scss'

export const LastSquare = (props) => {

    return (
        <div className={styles.square}>
            <div className={styles.main}>
                <p className={styles.last}>Poslední:</p>
                {props.text !== undefined ? <div className={styles.text}>{props.text}</div> : <div className={styles.text}>Tady se zobrazí poslední vytvořená položka</div>}
            </div>
        </div>
    )
}