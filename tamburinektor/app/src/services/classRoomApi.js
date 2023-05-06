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

const ClassRoomApi = {
    createClass, getCountClass, getLastClass
};
export default ClassRoomApi;