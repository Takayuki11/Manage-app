import axios from 'axios'

const TASK_API_BASE_URL = 'http://localhost:8080/api/tasks'

export default {
    
    actions: {
        getTasks(){
            return axios.get(TASK_API_BASE_URL)
        },

        getProcessingTasks(){
            return axios.get(TASK_API_BASE_URL + "/processing")
        },
    
        getCompletedTasks(){
            return axios.get(TASK_API_BASE_URL + "/completed")
        },
    
        getTask(_,taskId){
            return axios.get(TASK_API_BASE_URL + "/" + taskId)
        },
    
        createTask(_, params){
            const url = TASK_API_BASE_URL + "/create"
            return axios.post(url, params)
        },
    
        editTask(_, {taskId, task}){
            const url = TASK_API_BASE_URL + "/" + taskId
            return axios.put(url, task)
        },
    
        deleteTask(_, taskId){
            const url = TASK_API_BASE_URL + "/" + taskId
            return axios.delete(url)
        },
    
        sortTasksWithSchedule(_, {fromParams, toParams, task}){
            const url = TASK_API_BASE_URL + "/sort?from=" + fromParams + "&to=" + toParams
            return axios.put(url, task)
        }
    }
}