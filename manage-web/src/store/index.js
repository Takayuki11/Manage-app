import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import task from './modules/task'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        user: user,
        task: task
    }
})

export default store