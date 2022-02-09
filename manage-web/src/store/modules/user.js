import axios from 'axios'

const USER_API_BASE_URL = 'http://localhost:8080/api/users'

export default {
    state: {
        isLoggedIn: false
    },

    mutations: {
        loginSuccess(state){
            state.isLoggedIn = true
        }
    },

    actions: {
        signup({ commit }, params) {
            console.log(commit)
            return axios.post(USER_API_BASE_URL + '/signup', params)
        },
    
        login({ commit }, params){
            return axios.post(USER_API_BASE_URL + '/login', params)
                .then(() => {
                    commit("loginSuccess")
                })
        }
    }
}