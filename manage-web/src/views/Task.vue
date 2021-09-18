<template>
    <div class="container">
        <h1 class="text-center">Task List</h1>
        <div class="text-center">
            <v-btn @click="openCreateDialog()">+タスクの追加</v-btn>
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
                    <td @click="openEditor(); getTask(task.id);"> <button>編集</button></td>
                    <td><button @click="deleteTask(task.id)">削除</button></td>
                </tr>
            </tbody>
        </table>


        <!-- 新規作成ダイアログ -->
        <v-dialog
        v-model="createDialog"
        persistent
        max-width="600px"
        >
            <v-card>
                <v-card-title>
                    <span class="text-h5">タスクの追加</span>
                </v-card-title>
                <v-card-text>
                <v-text-field label="名前" v-model="newTask.name"></v-text-field>
                <v-text-field label="メールアドレス" v-model="newTask.email"></v-text-field>
                <v-text-field label="タスク" v-model="newTask.task"></v-text-field>
                
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="createDialog = false"
                >
                    キャンセル
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="createTask()"
                >
                    保存
                </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <!-- 編集ダイアログ -->
        <v-dialog
        v-model="editDialog"
        persistent
        max-width="600px"
        >
            <v-card>
                <v-card-title>
                    <span class="text-h5">タスクの編集</span>
                </v-card-title>
                <v-card-text>
                <v-text-field label="名前" v-model="newTask.name"></v-text-field>
                <v-text-field label="メールアドレス" v-model="newTask.email"></v-text-field>
                <v-text-field label="タスク" v-model="newTask.task"></v-text-field>
                
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="editDialog = false"
                >
                    キャンセル
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="editTask(newTask.id)"
                >
                    保存
                </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    
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
                id: '',
                name: '',
                email: '',
                task: '',
            },
            createDialog: false,
            editDialog: false,
        }
    },
    methods: {
        getTasks(){
            TaskService.getTasks().then((response) => {
                this.tasks = response.data
            });
        },
        getTask(taskId){
            const vm = this;
            TaskService.getTask(taskId).then((response) => {
                vm.newTask = response.data
                console.log(vm.newTask.id)
            })
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
                    vm.newTask.name = ''
                    vm.newTask.email = ''
                    vm.newTask.task = ''
                    vm.createDialog = false
                }).catch(() =>{

                })
        },
        editTask(taskId){
            const vm = this
            let params = {
                name: this.newTask.name,
                email: this.newTask.email,
                task: this.newTask.task
            }
            TaskService.editTask(taskId, params)
                .then(() => {
                    vm.editDialog = false
                    vm.getTasks()
                    vm.newTask.name = ''
                    vm.newTask.email = ''
                    vm.newTask.task = ''
                }).catch(() => {

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
        openCreateDialog(){
            this.createDialog = true
        },
        openEditor(){
            this.editDialog = true
        }
    },
    created() {
        this.getTasks()
    }
}
</script>
