<template>
    <div>
        <div v-if="isMaster()">
            <h1>社員登録</h1>
            <v-text-field label="名前" v-model="user.name"></v-text-field>
            <v-text-field label="メールアドレス" v-model="user.email"></v-text-field>
            <v-text-field label="パスワード" type="password" v-model="user.password"></v-text-field>
            <v-radio-group label="役割">
                <v-radio v-for="role in roles" :key="role" :value="role" :label="role" @click="assignRole(role)"></v-radio>
            </v-radio-group>
            <v-btn @click="registerUser()">登録</v-btn>
        </div>
        <div v-if="!isMaster()">
            <v-container class="overflow-hidden">
                <v-row>
                    <v-col cols="12" lg="9" class="bar">
                        <div class="customer-management g-3 m-1">顧客情報</div>
                        <v-row class="bar">
                            <v-col cols="12" lg="6" class="calendar g-3 m-1">カレンダー</v-col>
                            <v-col cols="12" lg="6" class="daily-report g-3 m-1">日報</v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="12" lg="3" class="bar">
                        <div class="task-management g-3 m-1">タスク管理</div>
                    </v-col>
                </v-row>
            </v-container>
        </div>
    </div>
</template>

<script>
import store from '../store/index'

export default {
  data(){
    return {
        user:{
            name: '',
            email: '',
            password: '',
            role: ''
        },
        masterUserRole: '',
        roles: ["manager", "user"]
    }
  },
  methods: {
    setUserRole(){
        this.masterUserRole = store.state.user.token.role
    },
    isMaster(){
        if(this.masterUserRole == 'master'){
            return true
        }
        return false
    },
    registerUser(){
        const self = this
        let params = {
            name: this.user.name,
            email: this.user.email,
            password: this.user.password,
            role: this.user.role,
            companyId: store.state.user.token.companyId
        }
        store.dispatch("registerEmployee", params)
            .then(() => {
                self.user.name = ''
                self.user.email = ''
                self.user.password = ''
                self.user.role = ''
            })
    },
    assignRole(role){
        this.user.role = role;
    },
  },
  created(){
    this.setUserRole()
  }
}
</script>

<style scoped>
    .customer-management {
        min-height: 400px;
        height: 50%;
        border: 1px black solid;
        border-radius: 3px;
    }

    .task-management {
        min-height: 800px;
        height: 100%;
        border: 1px black solid;
        border-radius: 3px;
    }

    .calendar, .daily-report {
        min-height: 400px;
        height: 50%;
        border: 1px black solid;
        border-radius: 3px;
        margin: 0;
        padding: 0;
    }

    .bar {
        padding: 0px;
        margin: 0;
    }

    @media screen and (max-width: 991px) {
        .customer-management {
            height: 300px;
            border: 1px black solid;
        }

        .task-management {
            min-height: 300px;
            height: 300px;
            border: 1px black solid;
        }

        .calendar, .daily-report {
            height: 300px;
            border: 1px black solid;
            margin: 0;
            padding: 0;
        }

        .bar {
            padding: 0px;
            margin: 0;
        }
        
    }
</style>