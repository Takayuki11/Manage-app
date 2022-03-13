import axios from "axios";

const COMPANY_API_BASE_URL = 'http://localhost:8080/api/company'

export default {
    actions: {
        saveCompany(_, params){
            return axios.post(COMPANY_API_BASE_URL + "/create", params)
        }
    }
}