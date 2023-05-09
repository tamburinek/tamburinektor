import styles from './CountSquare.module.scss'

export const CountSquare = (props) => {

    return (
        <div className={styles.square}>
            <div>{props.text}</div>
        </div>
    )
}