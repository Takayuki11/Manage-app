<template>
    <div class="container">
        <div class="text-center">
            <v-btn @click="openCreateDialog(); resetTaskData()">+タスクの追加</v-btn>
        </div>
        <v-container class="drag-area d-flex justify-space-around">
            <v-row>
                <v-col cols="4" id="box1" class="each-box">
                    <h2 class="text-center">タスク一覧</h2>
                    <draggable tag="ul" :options="{group: 'ITEMS'}" class="p-0">
                        <li v-for="task in tasks" :key="task.id" class="each-task">
                            <span class="each-task-name">{{ task.task_name }}</span>
                            <v-icon @click="openEditor(); getTask(task.id)" class="each-edit-icon">mdi-pencil</v-icon>
                            <!-- {{ task.task_note }} -->
                        </li>
                    </draggable>
                </v-col>
                <v-col cols="4" id="box2" class="each-box">
                    <h2 class="text-center">進行中</h2>
                    <draggable tag="ul" :options="{group: 'ITEMS'}" class="p-0">
                        <li v-for="task in tasks" :key="task.id"></li>
                    </draggable>
                </v-col>
                <v-col cols="4" id="box3" class="each-box">
                    <h2 class="text-center">完了</h2>
                    <draggable tag="ul" :options="{group: 'ITEMS'}" class="p-0">
                        <li v-for="task in tasks" :key="task.id"></li>
                    </draggable>
                </v-col>
            </v-row>
        </v-container>

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
                <v-text-field label="名前" v-model="newTask.task_name"></v-text-field>
                <v-text-field label="詳細" v-model="newTask.task_note"></v-text-field>
                
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
                <v-text-field label="名前" v-model="newTask.task_name"></v-text-field>
                <v-text-field label="詳細" v-model="newTask.task_note"></v-text-field>
                
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
import draggable from 'vuedraggable'

export default{
    name: 'tasks',
    components: {
        draggable,
    },
    data(){
        return {
            tasks: [],
            newTask: {
                id: '',
                task_name: '',
                task_note: '',
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
            })
        },
        createTask(){
            const vm = this;
            let params = {
                task_name: this.newTask.task_name,
                task_note: this.newTask.task_note
            }
            TaskService.createTask(params)
                .then(() => {
                    vm.getTasks()
                    vm.resetTaskData()
                    vm.createDialog = false
                }).catch(() =>{

                })
        },
        editTask(taskId){
            const vm = this
            let params = {
                task_name: this.newTask.task_name,
                task_note: this.newTask.task_note
            }
            TaskService.editTask(taskId, params)
                .then(() => {
                    vm.editDialog = false
                    vm.getTasks()
                    this.resetTaskData()
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
        },
        resetTaskData(){
            this.newTask.task_name = ''
            this.newTask.task_note = ''
        }
    },
    created() {
        this.getTasks()
    }
}
</script>

<style scoped>
.drag-area li{
    list-style: none;
    border: solid 1px black;
    border-radius: 5px;
    height: 80px;
    line-height: 80px;
    margin: 10px 0px;
}
.each-box {
    border: solid 1px black;
    border-radius: 5px;
    /* margin: 5px; */
}
</style>