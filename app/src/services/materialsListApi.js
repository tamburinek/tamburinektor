import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const getAllDefinitions = () => {
    console.log("getting all definitions")
    return (axios.get(`${baseUrl}/definition`,{headers}));
}

const getAllImages = () => {
    console.log("getting all images")
    return (axios.get(`${baseUrl}/image`,{headers}));
}

const getAllQuestions = () => {
    console.log("getting all questions")
    return (axios.get(`${baseUrl}/question`,{headers}));
}

const getAllTasks = () => {
    console.log("getting all tasks")
    return (axios.get(`${baseUrl}/task`,{headers}));
}

const getAllQuizes = () => {
    console.log("getting all quizes")
    return (axios.get(`${baseUrl}/quiz`,{headers}));
}

const getLastCreated = () => {
    console.log("getting last created")
    return (axios.get(`${baseUrl}/material/last`,{headers}));
}

const getDefinitionById = (id) => {
    console.log("getting definition with id: " + id)
    return (axios.get(`${baseUrl}/definition/${id}`,{headers}));
}

const getImageById = (id) => {
    console.log("getting image with id: " + id)
    return (axios.get(`${baseUrl}/image/${id}`,{headers}));
}

const getTaskById = (id) => {
    console.log("getting task with id: " + id)
    return (axios.get(`${baseUrl}/task/${id}`,{headers}));
}

const getQuestionById = (id) => {
    console.log("getting question with id: " + id)
    return (axios.get(`${baseUrl}/question/${id}`,{headers}));
}

const getQuizById = (id) => {
    console.log("getting quiz with id: " + id)
    return (axios.get(`${baseUrl}/quiz/${id}`,{headers}));
}


const getEntity = (id) => {
    console.log("getting entity with id: " + id)
    return (axios.get(`${baseUrl}/material/${id}`,{headers}));
}

const MaterialsListApi = {
    getAllDefinitions, getAllImages, getAllQuestions, getAllTasks, getAllQuizes, getLastCreated,
    getDefinitionById, getImageById, getTaskById, getQuestionById, getQuizById, getEntity
};
export default MaterialsListApi;