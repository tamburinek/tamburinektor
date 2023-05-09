import styles from "./ClassRoomSection.module.scss"
import {useEffect, useState} from "react";
import ClassRoomApi from "../../../../../services/classRoomApi";
import {ClassItem} from "./class-item/ClassItem";
import {MyClassItem} from "./myClass-item/MyClassItem";

export const ClassRoomSection = () => {

    const [allClasses, setClasses] = useState([])
    const [myClasses, setMyClasses] = useState([])
    const [activeClass, setActiveClass] = useState(-1)
    const [rerender, setRerender] = useState(false)

    const [password, setPassword] = useState("")

    const listClasses = allClasses.map((item) =>
        <ClassItem onClick={() => setActiveClass(item.id)} key={item.id} id={item.id} name={item.name} username={item.username} current={activeClass} my={myClasses}/>
    );

    const listMyClasses = myClasses.map((item) =>
        <MyClassItem key={item.id} id={item.id} name={item.name}/>
    );

    let confirm = (e) => {
        e.preventDefault()
        ClassRoomApi.addMeToClass(activeClass, password).then(res => {
            console.log(res)
        }).then(() => {
            ClassRoomApi.getAllClasses().then((res) => {
                setClasses(res.data)
            })
        }).then(() => {
            ClassRoomApi.getMyClasses().then((res) => {
                setMyClasses(res.data)
            })
        })
        if (rerender === true){
            setRerender(false)
        } else {
            setRerender(true)
        }
    }

    useEffect(() => {
        ClassRoomApi.getAllClasses().then((res) => {
            setClasses(res.data)
        })
        ClassRoomApi.getMyClasses().then((res) => {
            setMyClasses(res.data)
        })
    }, [])

    return(
      <div className={styles.main}>
          <div className={styles.myClasses}>
              <h2 className={styles.h2}>Moje třídy</h2>
              <div className={styles.classes} autoFocus={rerender}>
                  {listMyClasses}
              </div>
          </div>
          <div className={styles.addClass}>
              <h2 className={styles.h2}>Přidat třídu</h2>
              <div className={styles.available}>
                  <div className={styles.allClasses} autoFocus={rerender}>
                      {listClasses}
                  </div>
                  <div className={styles.form}>
                      {activeClass !== -1 && <div className={styles.password}>
                          <input onChange={(e) => setPassword(e.target.value)} value={password} placeholder={"Heslo..."} className={styles.input}/>
                      </div>}
                      {activeClass !== -1 && <div className={styles.button}>
                          <button onClick={confirm} className={styles.add}>Přidat se</button>
                      </div>}
                  </div>
              </div>
          </div>
      </div>
    )
}