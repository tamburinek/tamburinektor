import styles from './EditMaterialModal.module.scss'
import {DefinitionForm} from "../../../../../../parts/lecture-types/definition-form/DefinitionForm";

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";
import {QuizForm} from "../../../../../../parts/lecture-types/quiz-form/QuizForm";
import {TaskForm} from "../../../../../../parts/lecture-types/task-form/TaskForm";
import {QuestionForm} from "../../../../../../parts/lecture-types/question-form/QuestionForm";
import {ImageForm} from "../../../../../../parts/lecture-types/image-form/ImageForm";

export const EditMaterialModal = (props) => {

    console.log(props.id)

    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.form} onClick={event => event.stopPropagation()}>
                <h1 className={styles.h1}>Upravit materiál</h1>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                {props.type === "definition" && <DefinitionForm id={props.id} onEdit={props.onClose}/>}
                {props.type === "image" && <ImageForm id={props.id} onEdit={props.onClose}/>}
                {props.type === "question" && <QuestionForm id={props.id} onEdit={props.onClose}/>}
                {props.type === "task" && <TaskForm id={props.id} onEdit={props.onClose}/>}
                {props.type === "quiz" && <QuizForm id={props.id} onEdit={props.onClose}/>}
            </div>
        </div>
    )
}