import styles from './EditMaterialModal.module.scss'
import {DefinitionForm} from "../../../../../../parts/lecture-types/definition-form/DefinitionForm";

import closeImage from "../../../../../../assets/png/close.png"

import {useEffect, useState} from "react";
import {QuizForm} from "../../../../../../parts/lecture-types/quiz-form/QuizForm";
import {TaskForm} from "../../../../../../parts/lecture-types/task-form/TaskForm";
import {QuestionForm} from "../../../../../../parts/lecture-types/question-form/QuestionForm";
import {ImageForm} from "../../../../../../parts/lecture-types/image-form/ImageForm";

export const EditMaterialModal = (props) => {
    return (
        <div className={styles.main} onClick={props.onClose}>
            <div className={styles.form} onClick={event => event.stopPropagation()}>
                <img onClick={props.onClose} className={styles.close} src={closeImage} alt={"close"}/>
                {props.type === "definition" && <DefinitionForm/>}
                {props.type === "image" && <ImageForm/>}
                {props.type === "question" && <QuestionForm/>}
                {props.type === "task" && <TaskForm/>}
                {props.type === "quiz" && <QuizForm/>}
            </div>
        </div>
    )
}