import axios from 'axios'

const TASK_API_BASE_URL = 'http://localhost:8080/api/tasks'

class TaskService{
    getTasks(){
        return axios.get(TASK_API_BASE_URL)
    }

    getProcessingTasks(){
        return axios.get(TASK_API_BASE_URL + "/processing")
    }

    getCompletedTasks(){
        return axios.get(TASK_API_BASE_URL + "/completed")
    }

    getTask(taskId){
        return axios.get(TASK_API_BASE_URL + "/" + taskId)
    }

    createTask(params){
        const url = TASK_API_BASE_URL + "/create"
        return axios.post(url, params)
    }

    editTask(taskId, task){
        const url = TASK_API_BASE_URL + "/" + taskId
        return axios.put(url, task)
    }

    deleteTask(taskId){
        const url = TASK_API_BASE_URL + "/" + taskId
        return axios.delete(url)
    }

    sortTasksWithSchedule(fromParams, toParams, task){
        const url = TASK_API_BASE_URL + "/sort?from=" + fromParams + "&to=" + toParams
        return axios.put(url, task)
    }
}

export default new TaskService()