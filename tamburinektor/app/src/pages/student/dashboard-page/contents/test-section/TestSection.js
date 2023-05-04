import styles from "./TestSection.module.scss"

import {TestItem} from "./test-item/TestItem";

export const TestSection = (props) => {

    let boxItems = ["prvni velmi dlouha definice ktera se cela nevejde zrovna do tohoto okna", "druha definice", 1,2,3,4,5,6,2,3,3,3,3,3,3,3,3]
    //let boxItems = ["prvni lekce", "druha lekce"]
    const listBoxItems = boxItems.map((item) =>
        <TestItem item={item}/>
    );

    let classes = ["4A", "4B"]
    const listClasses = classes.map((item) =>
        <option>{item}</option>
    );


    return(
        <div className={styles.main}>
            <select className={styles.select}>
                {listClasses}
            </select>
            <div className={styles.filter}>
                <input className={styles.search} type={"text"} placeholder={"Vyhledat...."}/>
                <select className={styles.filterName}>
                    <option>Nejnovější</option>
                    <option>Nejstarší</option>
                    <option>Abecedně vzestupně</option>
                    <option>Abecedně sestupně</option>
                </select>
            </div>
            <div className={styles.menu}>
                <div className={styles.items}>
                    {listBoxItems}
                </div>
            </div>
        </div>
    )
}