import axios from "axios";

const { REACT_APP_API_ENDPOINT } = process.env;


//creating a JWT and storing it in localStorage after Login
const login = (username, password) => {
  return axios.post(`${REACT_APP_API_ENDPOINT}/auth/signin`, {
      username,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
    });
};

//Logout: Removing JWT from localStorage 
const logout = () => {
  localStorage.removeItem("user");
};


//Getting current user
const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};


const AuthService = {
  login,
  logout,
  getCurrentUser,
};
export default AuthService;