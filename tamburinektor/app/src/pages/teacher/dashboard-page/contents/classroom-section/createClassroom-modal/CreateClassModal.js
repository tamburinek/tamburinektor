import styles from './CreateClassModal.module.scss'

export const CreateClassModal = () => {

    return (
        <div className={styles.main}>
            <form className={styles.form}>
                <input placeholder={"Název třídy"}/>
                <button>Přídat</button>
            </form>
        </div>
    )
}