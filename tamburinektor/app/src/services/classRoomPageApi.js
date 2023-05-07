import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const addLectureToClass = (id, lecture) => {
    console.log("adding lecture " + lecture + " to class " + id)
    return (axios.post(`${baseUrl}/class/${id}/lecture/${lecture}`,
        {
            "lectureId": lecture
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const removeLectureFromClass = (id, lecture) => {
    console.log("removing lecture " + lecture + " to class " + id)
    return (axios.delete(`${baseUrl}/class/${id}/lecture/${lecture}`,{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const getAllLecturesOfClass = (id) => {
    console.log("geting lectures from class " + id)
    return (axios.get(`${baseUrl}/class/${id}/lecture`,{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const addTestToClass = (id, test) => {
    console.log("adding test " + test + " to class " + id)
    return (axios.post(`${baseUrl}/class/${id}/test/${test}`,
        {
            "lectureId": test
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const removeTestFromCLass = (id, test) => {
    console.log("removing test " + test + " to class " + id)
    return (axios.delete(`${baseUrl}/class/${id}/test/${test}`,{headers}).then(response => {
        console.log(response)
        return response
    }));
}

const getAllTestsOfClass = (id) => {
    console.log("geting test from class " + id)
    return (axios.get(`${baseUrl}/class/${id}/test`,{headers}).then(response => {
        console.log(response)
        return response
    }));
}


const ClassRoomApi = {
    addLectureToClass, removeLectureFromClass, getAllLecturesOfClass, addTestToClass, removeTestFromCLass, getAllTestsOfClass
};
export default ClassRoomApi;