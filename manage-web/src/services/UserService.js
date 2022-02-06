import axios from "axios";

const USER_API_BASE_URL = 'http://localhost:8080/api/users'

class UserService{
    createUser(params){
        return axios.post(USER_API_BASE_URL + '/signup', params)
    }

    login(params){
        return axios.post(USER_API_BASE_URL + '/login', params)
    }
}

export default new UserService()