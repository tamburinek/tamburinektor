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

const MaterialsListApi = {
    getAllDefinitions, getAllImages, getAllQuestions, getAllTasks, getAllQuizes, getLastCreated
};
export default MaterialsListApi;