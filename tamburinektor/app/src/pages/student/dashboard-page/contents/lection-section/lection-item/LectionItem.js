import styles from "./LectionItem.module.scss"

export const LectionItem = (props) => {
  return(
      <div className={styles.lecture}>
          <div className={styles.box}>
              <p>{props.item}</p>
              <button className={styles.button}>Spustit</button>
          </div>
      </div>
  )
}