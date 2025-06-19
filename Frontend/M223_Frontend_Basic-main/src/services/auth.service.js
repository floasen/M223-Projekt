import axios from "axios";
const API_URL = "http://localhost:8080/";

const login = (username, password) => {
    return axios
        .post(API_URL + "login",
            { username, password, })
        .then((response) => {
            if (response.data.username) {
                localStorage.setItem("user",
                    JSON.stringify(response.data));
                return response.data;
            }
        }).catch((error) => {
            console.log(error);
            throw error;
        })
};
const logout = () => {
    localStorage.removeItem("user");
};
const getCurrentUser = () => {
    return JSON.parse(
        localStorage.getItem("user")
    );
};
const AuthService = {
    login,
    logout,
    getCurrentUser,
}
export default AuthService;