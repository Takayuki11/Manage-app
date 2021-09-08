import axios from 'axios'

const TASK_API_BASE_URL = 'http://localhost:8080/api/tasks'

class TaskService{
    getTasks(){
        return axios.get(TASK_API_BASE_URL);
    }

    getTask(taskId){
        return axios.get(TASK_API_BASE_URL + "/" + taskId)
    }

    createTask(params){
        const url = TASK_API_BASE_URL + "/create"
        return axios.post(url, params)
    }

    editTask(taskId, params){
        const url = TASK_API_BASE_URL + "/" + taskId
        return axios.put(url, params)
    }

    deleteTask(taskId){
        const url = TASK_API_BASE_URL + "/" + taskId
        return axios.delete(url);
    }
}

export default new TaskService()