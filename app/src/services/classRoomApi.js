import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const createClass = (name, password) => {
    console.log("creating class " + name)
    return (axios.post(`${baseUrl}/class`,
        {
            "name": name,
            "password": password
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const getCountClass = () => {
    console.log("getting count of classes")
    return (axios.get(`${baseUrl}/class/count`,{headers}));
}

const getLastClass = () => {
    console.log("getting last class")
    return (axios.get(`${baseUrl}/class/last`,{headers}));
}

const getAllClassesCreated = () => {
    console.log("getting all classes")
    return (axios.get(`${baseUrl}/class`,{headers}));
}

const getAllClasses = () => {
    console.log("getting all classes")
    return (axios.get(`${baseUrl}/class/all`,{headers}));
}

const getMyClasses = () => {
    console.log("getting my classes")
    return (axios.get(`${baseUrl}/class/my`,{headers}));
}

const addMeToClass = (id, password) => {
    console.log("add me to class " + id + " " + password)
    return (axios.post(`${baseUrl}/class/${id}`, {
        password:password
    },{headers}));
}

const getAllStudentsOfClass = (id) => {
    console.log("getting all students of class " + id)
    return (axios.get(`${baseUrl}/class/${id}/users`,{headers}));
}

const ClassRoomApi = {
    createClass, getCountClass, getLastClass, getAllClassesCreated, getAllClasses, getMyClasses, addMeToClass, getAllStudentsOfClass
};
export default ClassRoomApi;