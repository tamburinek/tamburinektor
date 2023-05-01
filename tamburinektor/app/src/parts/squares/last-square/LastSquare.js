import styles from './LastSquare.module.scss'

export const LastSquare = (props) => {

    return (
        <div className={styles.square}>
            <div className={styles.main}>
                {props.text !== undefined ? <div className={styles.text}>{props.text}</div> : <div className={styles.text}>Tady se zobrazí poslední vytvořená položka</div>}
                <div className={styles.buttons}>
                    {props.text !== undefined && <button className={styles.edit} onClick={props.onClick}>Upravit</button>}
                    {props.text !== undefined && props.button !== undefined && <button onClick={props.customClick} className={styles.custom}>{props.button}</button>}
                </div>
            </div>
        </div>
    )
}