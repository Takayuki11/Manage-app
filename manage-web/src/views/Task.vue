<template>
    <div class="container">
        <div class="text-center">
            <v-btn @click="openCreateDialog(); resetTaskData()">+タスクの追加</v-btn>
        </div>
         <v-container class="drag-area d-flex">
            <v-row>
                <v-col cols="12" sm="4" class="each-box">
                    <h2 class="text-center">タスク一覧</h2>
                    <draggable tag="ul" :options="{group: 'ITEMS'}" class="incomplete p-0" @end="onEnd($event)">
                        <li v-for="task in incompleteTasks" :key="task.id"  :id="task.id" @dragstart="setCurrentTask(task)">
                            <span class="each-task-name">{{ task.taskName }}</span>
                            <v-icon @click="openEditor(); getTask(task.id); setCurrentTask(task)" class="each-edit-icon">mdi-pencil</v-icon>
                            <!-- {{ task.taskNote }} -->
                        </li>
                    </draggable>
                </v-col>
                <v-col cols="12" sm="4" class="each-box">
                    <h2 class="text-center">進行中</h2>
                    <draggable tag="ul" :options="{group: 'ITEMS'}" class="processing p-0" @end="onEnd($event)">
                        <li v-for="task in processingTasks" :key="task.id" :id="task.id" @dragstart="setCurrentTask(task)">
                            <span class="each-task-name">{{ task.taskName }}</span>
                            <v-icon @click="openEditor(); getTask(task.id); setCurrentTask(task)" class="each-edit-icon">mdi-pencil</v-icon>
                        </li>
                    </draggable>
                </v-col>
                <v-col cols="12" sm="4" class="each-box">
                    <h2 class="text-center">完了</h2>
                    <draggable tag="ul" :options="{group: 'ITEMS'}" class="completed p-0" @end="onEnd($event)">
                        <li v-for="task in completedTasks" :key="task.id" :id="task.id" @dragstart="setCurrentTask(task)">
                            <span class="each-task-name">{{ task.taskName }}</span>
                            <v-icon @click="openEditor(); getTask(task.id); setCurrentTask(task)" class="each-edit-icon">mdi-pencil</v-icon>
                        </li>
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
                <v-text-field label="名前" v-model="task.taskName"></v-text-field>
                <v-text-field label="詳細" v-model="task.taskNote"></v-text-field>
                
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
                <v-text-field label="名前" v-model="task.taskName"></v-text-field>
                <v-text-field label="詳細" v-model="task.taskNote"></v-text-field>
                
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
                    @click="editTask(task.id)"
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
            incompleteTasks: [],
            processingTasks: [],
            completedTasks: [],
            task: {
                id: '',
                taskName: '',
                taskNote: '',
                taskStatus: '',
                sortNumber: '',
                scheduleStatus: ''
            },
            createDialog: false,
            editDialog: false,
        }
    },
    methods: {
        getIncompleteTasks(){
            TaskService.getTasks().then((response) => {
                this.incompleteTasks = response.data
            });
        },
        getProcessingTasks(){
            TaskService.getProcessingTasks().then((response) => {
                this.processingTasks = response.data
            })
        },
        getCompletedTasks(){
            TaskService.getCompletedTasks().then((response) => {
                this.completedTasks = response.data
            })
        },
        getTask(taskId){
            const vm = this;
            TaskService.getTask(taskId).then((response) => {
                vm.task = response.data
            })
        },
        createTask(){
            const vm = this;
            let params = {
                taskName: this.task.taskName,
                taskNote: this.task.taskNote
            }
            TaskService.createTask(params)
                .then(() => {
                    vm.getIncompleteTasks()
                    vm.resetTaskData()
                    vm.createDialog = false
                }).catch(() =>{

                })
        },
        editTask(taskId){
            const vm = this
            TaskService.editTask(taskId, this.task)
                .then(() => {
                    vm.editDialog = false
                    vm.getIncompleteTasks()
                    vm.getProcessingTasks()
                    vm.getCompletedTasks()
                }).catch(() => {

                })
        },
        deleteTask(taskId){
            const getTasks = this.getIncompleteTasks;
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
            this.task.id = ''
            this.task.taskName = ''
            this.task.taskNote = ''
        },     
        setCurrentTask(currrentTask){
            this.task = currrentTask
        },
        // vuedraggableのイベント
       onEnd(event){
            let taskStatus = event.to.classList[0]
            if(taskStatus == 'completed'){
                this.task.scheduleStatus = true
            } else {
                this.task.scheduleStatus = false
            }
            this.task.taskStatus = taskStatus

            let toParams  = []
            let fromParams = []

            for(let i = 0; i < event.to.childNodes.length; i++){
                toParams.push(Number(event.to.childNodes[i].id))
            }
            for(let i = 0; i < event.from.childNodes.length; i++){
                fromParams.push(Number(event.from.childNodes[i].id))
            }
            
            const vm = this
            TaskService.sortTasksWithSchedule(fromParams, toParams, this.task)
            .then(() => {
                vm.getIncompleteTasks()
                vm.getProcessingTasks()
                vm.getCompletedTasks()
            })
       }
    },
    created() {
        this.getIncompleteTasks()
        this.getProcessingTasks()
        this.getCompletedTasks()
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
}
</style>