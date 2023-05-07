import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const createLecture = (description, entities) => {
    console.log("trying to create lecture: " + description + " " + entities.length)
    return (axios.post(`${baseUrl}/lecture`,
        {
            "description": description,
            "lectureEntities": entities
        },{headers}).then(response => {
        console.log(response)
        return response
    }));
}


const MaterialsApi = {
    createLecture
};
export default MaterialsApi;