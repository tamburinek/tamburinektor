import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";

const headers = authHeader();

const getCountMaterial = () => {
    console.log("getting count of materials")
    return (axios.get(`${baseUrl}/material/count`,{headers}));
}


const CountApi = {
    getCountMaterial
};
export default CountApi;