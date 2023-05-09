import styles from "./DefinitionEntity.module.scss"
import {useEffect, useState} from "react";
import MaterialsListApi from "../../../../../services/materialsListApi";

export const DefinitionEntity = (props) => {

    const [entity, setEntity] = useState({})

    useEffect(() => {
        MaterialsListApi.getDefinitionById(props.id).then(res => {
            console.log(res.data)
            setEntity(res.data)
        })
    },[])

    return(
        <div className={styles.main}>
            <div className={styles.description}>
                {entity.description}
            </div>
            <div className={styles.definition}>
                {entity.definition}
            </div>
            <div className={styles.image}>
                 <img className={styles.oneImage} src={entity.imageUrl} alt={"definice"} onError={event => event.target.style.display = 'none'}/>
            </div>
        </div>
    )
}