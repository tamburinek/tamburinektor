import styles from './LastSquare.module.scss'

export const LastSquare = (props) => {

    return (
        <div className={styles.square}>
            <div>{props.text}</div>
        </div>
    )
}