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

const createTest = (description, questions) => {
    console.log("creating test " + description + " " + questions.length)
    return (axios.post(`${baseUrl}/test`,{
        "description": description,
        "assignments": questions
    },{headers}).then((response) => {
        console.log(response)
        return response
    }));
}

const getAllTests = () => {
    console.log("getting all tests")
    return (axios.get(`${baseUrl}/test`,{headers}));
}

const getTestById = (id) => {
    console.log("getting test by id " + id)
    return (axios.get(`${baseUrl}/test/${id}`,{headers}));
}

const updateTest = (id, description, questions) => {
    console.log("updating test " + description + " " + questions.length)
    return (axios.patch(`${baseUrl}/test/${id}`,{
        "description": description,
        "assignments": questions
    },{headers}).then((response) => {
        console.log(response)
        return response
    }));
}

const getLastTest = () => {
    console.log("getting last test")
    return (axios.get(`${baseUrl}/test/last`,{headers}));
}

const getAssignmentById = (id) => {
    console.log("getting assignment " + id)
    return (axios.get(`${baseUrl}/assignment/${id}`,{headers}));
}

const updateClosedQuestion = (id, question, imageLink, rightAnswers, wrongAnswers) => {
    console.log("updating close question")
    return (axios.patch(`${baseUrl}/assignment`,{
        "id" : id,
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

const updateOpenQuestion = (id, question, imageLink, answer) => {
    let helper = []
    helper.push(answer)
    console.log("updating open question")
    return (axios.patch(`${baseUrl}/assignment`,{
        "id" : id,
        "question": question,
        "imageLink": imageLink,
        "openQuestion":true,
        "rightAnswers":helper
    },{headers}).then((response) => {
        console.log(response)
        return response
    }));
}

const TestApi = {
    createOpenTestQuestion, createCloseTestQuestion, getAllClose, getAllOpen, createTest,
    getAllTests, getTestById, updateTest, getLastTest, getAssignmentById, updateClosedQuestion, updateOpenQuestion
};
export default TestApi;