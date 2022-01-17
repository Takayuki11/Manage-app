<template>
    <div class="container">
        <h1 >Task管理アプリ</h1>
        <v-text-field label="名前" v-model="name"></v-text-field>
        <v-text-field label="メールアドレス" v-model="email"></v-text-field>
        <v-text-field label="パスワード" type="password" v-model="password"></v-text-field>

        <v-btn @click="signUp()">Sign up</v-btn>
    </div>
</template>

<script>
import UserService from "../services/UserService"

export default {
    name: 'Signup',
    data(){
        return{
            name: '',
            email: '',
            password: '',
        }
    },
    methods: {
        signUp(){
            let params = {
                name: this.name,
                email: this.email,
                password: this.password
            }
            UserService.createUser(params)
                .then(() => {
                    this.resetParameter()
                })
        },
        resetParameter(){
            this.name = '',
            this.email = '',
            this.password = ''
        }
    },
    computed: {

    }
}
</script>

<style scoped>
.eye {
    position: absolute;
    left: 10px;
}
</style>