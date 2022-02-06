<template>
    <div class="container">
        <h1>ログイン</h1>
        <v-text-field label="メールアドレス" v-model="email"></v-text-field>
        <v-text-field label="パスワード" type="password" v-model="password"></v-text-field>

        <v-btn @click="login()">Login</v-btn>
    </div>
</template>

<script>
import UserService from "../services/UserService"

export default {
    name: 'Login',
    data(){
        return{
            email: '',
            password: '',
        }
    },
    methods: {
        login(){
            let params = {
                email: this.email,
                password: this.password
            }
            UserService.login(params)
                .then(() => {
                    this.resetParameter()
                })
        },
        resetParameter(){
            this.email = '',
            this.password = ''
        }
    }
}
</script>