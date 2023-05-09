import axios from 'axios';
import {baseUrl} from "../config/const";
import authHeader from "./auth-header";


const login = (username, password) => {
    return (axios.post(`${baseUrl}/users/signin`,
        {
            "username": username,
            "password": password
        }).then(response => {
            if (response.data.token) {
                localStorage.setItem('token', response.data.token);
            }
            if (response.data.username) {
                localStorage.setItem('username', response.data.username);
            }
            if (response.data.role) {
                localStorage.setItem('role', response.data.role);
            }
        console.log(response.data)
        return response.data
    }));
}

const logout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
    localStorage.removeItem('token');
    window.location = "/"
}

const register = (name, surname, username, password, userType) => {
    console.log(name, surname, username, password, userType)
    return (axios.post(`${baseUrl}/users`, {
        "firstName": name,
        "lastName": surname,
        "username": username,
        "password": password,
        "userType": userType
    }));
}

const getCurrentUser = () => {
    const headers = authHeader();
    console.log(headers)
    return axios.get(`${baseUrl}/users/me`, {headers})
}

const AuthService = {
    register, login, logout, getCurrentUser,
};
export default AuthService;