import axios from "axios";

const USER_API_BASE_URL = 'http://localhost:8080/api/users'

class UserService{
    createUser(params){
        return axios.post(USER_API_BASE_URL + '/create', params)
    }
}

export default new UserService()