import styles from "./LectionItem.module.scss"

export const LectionItem = (props) => {
  return(
      <div className={styles.lecture}>
          <div className={styles.box}>
              <p>{props.item}</p>
              <button onClick={() => window.location = "lecture?id=" + props.id} className={styles.button}>Spustit</button>
          </div>
      </div>
  )
}