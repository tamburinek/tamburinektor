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

const MaterialsApi = {
    createDefinition, createImage
};
export default MaterialsApi;