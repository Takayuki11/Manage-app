<template>
    <div class="container">
        <h1 class="text-center">Task List</h1>
        <div class="text-center">
            <h2>新規作成</h2>
            <input type="text" v-model="newTask.name">
            <input type="text" v-model="newTask.email">
            <input type="text" v-model="newTask.task">
            <input type="submit" value="送信" @click="createTask()"> 
        </div>
        <table class="table table-striped">
            <thead>
                <th>Id</th>
                <th>name</th>
                <th>email</th>
                <th>Task</th>
                <th></th>
                <th></th>
            </thead>
            <tbody>
                <tr v-for="task in tasks" :key="task.id">
                    <td>{{ task.id }}</td>
                    <td>{{ task.name }}</td>
                    <td>{{ task.email }}</td>
                    <td>{{ task.task }}</td>
                    <td><router-link tag="button" :to="'/tasks/edit/' + task.id ">編集</router-link></td>
                    <td><button @click="deleteTask(task.id)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import TaskService from '../services/TaskService'

export default{
    name: 'tasks',
    data(){
        return {
            tasks: [],
            newTask: {
                name: '',
                email: '',
                task: '',
            }
        }
    },
    methods: {
        getTasks(){
            TaskService.getTasks().then((response) => {
                this.tasks = response.data;
            });
        },
        createTask(){
            const vm = this;
            let params = {
                name: this.newTask.name,
                email: this.newTask.email,
                task: this.newTask.task
            }
            TaskService.createTask(params)
                .then(() => {
                    vm.getTasks()
                    console.log(params)
                    vm.newTask.name = ''
                    vm.newTask.email = ''
                    vm.newTask.task = ''
                }).catch(() =>{

                })
        },
        deleteTask(taskId){
            const getTasks = this.getTasks;
            TaskService.deleteTask(taskId)
                .then(() =>{
                    getTasks()
                }).catch(() => {

                })
        },
    },
    created() {
        this.getTasks()
    }
}
</script>
