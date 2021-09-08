<template>
    <div class="edit">
        <h1>タスクの編集</h1>
        <input type="text" v-model="newTask.name">
        <input type="text" v-model="newTask.email">
        <input type="text" v-model="newTask.task">
        <input type="submit" @click="editTask($route.params.taskId)" value="編集">
    </div>
</template>

<script>
import TaskService from '../services/TaskService'

export default{
    name: 'edit',
    data(){
        return {
            newTask: {
                name: '',
                email: '',
                task: '',
            }
        }
    },
    methods: {
        editTask(taskId){
            const vm = this
            const params = {
                name: this.newTask.name,
                email: this.newTask.email,
                task: this.newTask.task
            }
            TaskService.editTask(taskId, params)
                .then(() => {
                    vm.$router.push("/tasks", () => {})
                }).catch(() => {

                })
        }
    },
    created() {
        const vm = this
        TaskService.getTask(this.$route.params.taskId)
            .then((response) => {
                vm.newTask = response.data
            })
    }
}
</script>