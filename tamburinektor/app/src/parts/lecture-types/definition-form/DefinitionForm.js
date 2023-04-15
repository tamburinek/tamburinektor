import styles from "./DefinitionForm.module.scss"

export const DefinitionForm = () => {

    let confirm = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <form>
                <button onClick={confirm} className={styles.add}>Přídat</button>
            </form>
        </div>
    )
}