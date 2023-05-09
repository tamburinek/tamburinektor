import styles from './GraphSquare.module.scss'

export const GraphSquare = (props) => {

    return (
        <div className={styles.square}>
            <div>{props.text}</div>
        </div>
    )
}