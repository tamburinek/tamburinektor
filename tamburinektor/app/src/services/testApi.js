import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const createOpenTestQuestion = (question, imageLink, answer) => {
    let helper = []
    helper.push(answer)
    console.log("creating open question")
    return (axios.post(`${baseUrl}/assignment`,{
        "question": question,
        "imageLink": imageLink,
        "openQuestion":true,
        "rightAnswers":helper
    },{headers}).then((response) => {
        console.log(response)
        return response
    }));
}

const createCloseTestQuestion = (question, imageLink, rightAnswers, wrongAnswers) => {
    console.log("creating close question")
    return (axios.post(`${baseUrl}/assignment`,{
        "question": question,
        "imageLink": imageLink,
        "openQuestion":false,
        "rightAnswers":rightAnswers,
        "wrongAnswers":wrongAnswers
    },{headers}).then((response) => {
        console.log(response)
        return response
    }));
}

const getAllClose = () => {
    console.log("getting all closed question for test")
    return (axios.get(`${baseUrl}/assignment/close`,{headers}));
}

const getAllOpen = () => {
    console.log("getting all open question for test")
    return (axios.get(`${baseUrl}/assignment/open`,{headers}));
}

const TestApi = {
    createOpenTestQuestion, createCloseTestQuestion, getAllClose, getAllOpen
};
export default TestApi;