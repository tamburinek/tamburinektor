export default function authHeader() {
    return {
        Authorization: 'Bearer ' + localStorage.token, 'Content-Type': 'application/json',
        'Accept': 'application/json'
    };
}