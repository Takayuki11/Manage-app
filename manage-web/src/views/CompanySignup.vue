<template>
    <div class="container">
        <h1>会社登録</h1>
        <v-text-field label="会社名" v-model="company.companyName"></v-text-field>
        <v-text-field label="マスターユーザー名" v-model="user.name"></v-text-field>
        <v-text-field label="メールアドレス" v-model="user.email"></v-text-field>
        <v-text-field label="パスワード" type="password" v-model="user.password"></v-text-field>
        <v-btn @click="registerCompany()">登録</v-btn>
    </div>
</template>

<script>
import store from '../store/index'

export default {
    data(){
        return{
            company: {
                companyName: ''
            },
            user: {
                name: '',
                email: '',
                password: ''
            }
        }
    },
    methods: {
        registerCompany(){
            const self = this
            // let params = {
            //     companyName: this.company.companyName,
            //     name: this.user.name,
            //     email: this.user.email,
            //     password: this.user.password,
            // }
            let params = {
                companyName: this.company.companyName,
                name: this.user.name,
                email: this.user.email,
                password: this.user.password,
            }
            store.dispatch("saveCompany", params).then(() => {
                self.company.companyName = ''
                self.user.name = ''
                self.user.email = ''
                self.user.password = ''
            })
        }
    }
}
</script>
