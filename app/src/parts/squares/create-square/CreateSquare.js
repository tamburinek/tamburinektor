import styles from './CreateSquare.module.scss'

export const CreateSquare = (props) => {

    return (
        <div className={styles.square}>
            <div>{props.text}</div>
        </div>
    )
}