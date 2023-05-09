import styles from './AllSquare.module.scss'

export const AllSquare = (props) => {

    return (
        <div className={styles.square}>
            <div>{props.text}</div>
        </div>
    )
}