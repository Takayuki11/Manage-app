import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import task from './modules/task'
import company from './modules/company'
import createPersistedstate from "vuex-persistedstate"

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        user: user,
        task: task,
        company: company
    },
    plugins: [createPersistedstate()]
})

export default store