import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const createImage = (description, imageUrl) => {
    console.log("trying to create image: " + description + " " + imageUrl)
    return (axios.post(`${baseUrl}/image`,
        {
            "description": description,
            "imageUrl" : imageUrl
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const updateImage = (id, description, imageUrl) => {
    console.log("trying to update image: " + description + " " + imageUrl)
    return (axios.patch(`${baseUrl}/image/${id}`,
        {
            "description": description,
            "imageUrl" : imageUrl
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const createDefinition = (description, definition, imageUrl) => {
    console.log("trying to create definition: " + description + " " + definition)
    return (axios.post(`${baseUrl}/definition`,
        {
            "description": description,
            "definition": definition,
            "imageUrl" : imageUrl
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const updateDefinition = (id, description, definition, imageUrl) => {
    console.log("trying to create definition: " + description + " " + definition)
    return (axios.patch(`${baseUrl}/definition/${id}`,
        {
            "description": description,
            "definition": definition,
            "imageUrl" : imageUrl
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const createQuestion = (question, anonymous) => {
    console.log("trying to create question: " + question + " " + anonymous)
    return (axios.post(`${baseUrl}/question`,
        {
            "questionText": question,
            "anonymous": anonymous
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const updateQuestion = (id, question, anonymous) => {
    console.log("trying to update question: " + question + " " + anonymous)
    return (axios.patch(`${baseUrl}/question/${id}`,
        {
            "questionText": question,
            "anonymous": anonymous
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const createTask = (question, questionImage, answer, answerImage) => {
    console.log("trying to create task: " + question + " " + answer)
    return (axios.post(`${baseUrl}/task`,
        {
            "question": question,
            "answer": answer,
            "questionImage": questionImage,
            "answerImage": answerImage
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const updateTask = (id, question, questionImage, answer, answerImage) => {
    console.log("trying to update task: " + question + " " + answer)
    return (axios.patch(`${baseUrl}/task/${id}`,
        {
            "question": question,
            "answer": answer,
            "questionImage": questionImage,
            "answerImage": answerImage
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const createQuiz = (name, questions) => {
    console.log("trying to create quiz: " + name + " " + questions.length)
    return (axios.post(`${baseUrl}/quiz`,
        {
            "name": name,
            "questions": questions
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const updateQuiz = (id, name, questions) => {
    console.log("trying to update quiz: " + name + " " + questions.length)
    return (axios.patch(`${baseUrl}/quiz/${id}`,
        {
            "name": name,
            "questions": questions
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const MaterialsApi = {
    createDefinition, createImage, createQuestion, createTask, updateDefinition, updateImage, updateTask, updateQuestion,
    createQuiz, updateQuiz
};
export default MaterialsApi;