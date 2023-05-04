import styles from "./ClassRoomSection.module.scss"

export const ClassRoomSection = () => {
  return(
      <div className={styles.main}>
          <div className={styles.myClasses}>
              <h2>Moje třídy</h2>
              <div className={styles.classes}>

              </div>
          </div>
          <div className={styles.addClass}>
              <h2>Přidat třídu</h2>
              <div className={styles.available}>

              </div>
          </div>
      </div>
  )
}