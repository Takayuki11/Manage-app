import axios from 'axios'

const USER_API_BASE_URL = 'http://localhost:8080/api/users'

export default {
    state: {
        isLoggedIn: false,
        token: {}
    },

    getters: {
        getToken(state){
            return state.token;
        }
    },

    mutations: {
        onLoginSuccess(state){
            state.isLoggedIn = true
        },

        onLogoutSuccess(state){
            state.isLoggedIn = false
            state.token = {}
        },

        decodeJWT(state, token){
            if(token){
                const base64Url = token.split('.')[1];                             
                const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                state.token = JSON.parse(decodeURIComponent(escape(window.atob(base64))));
            }
        }
    },

    actions: {
        signup(_, params) {
            return axios.post(USER_API_BASE_URL + '/signup', params)
        },
    
        login({ commit }, params){
            return axios.post(USER_API_BASE_URL + '/login', params)
                .then((response) => {
                    const token = response.data;
                    localStorage.setItem("JWTToken", token);
                    commit("onLoginSuccess")
                    commit("decodeJWT", token)
                })
        },

        registerEmployee(_, params){
            return axios.post(USER_API_BASE_URL + '/create', params)
        }
    }
}