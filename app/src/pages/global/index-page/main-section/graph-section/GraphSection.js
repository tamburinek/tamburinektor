import graphUp from "../../../../../assets/graphs/graphUp.png";
import graphLeft from "../../../../../assets/graphs/graphLeft.png";
import graphRight from "../../../../../assets/graphs/graphRight.png";

import styles from './GraphSection.module.scss'

export const GraphSection = () => {
    return (
        <div className={styles.graphs}>
            <img className={styles.graphUp} src={graphUp} alt={'graphUp'}/>
            <div className={styles.down}>
                <img className={styles.graphLeft} src={graphLeft} alt={'graphLeft'}/>
                <img className={styles.graphRight} src={graphRight} alt={'graphRight'}/>
            </div>
        </div>
    )
}