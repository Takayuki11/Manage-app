<template>
    <div class="container">
        <h1>ログイン</h1>
        <p class="validationMassage"></p>
        <v-text-field label="メールアドレス" v-model="email"></v-text-field>
        <v-text-field label="パスワード" type="password" v-model="password"></v-text-field>

        <v-btn @click="login()">Login</v-btn>
    </div>
</template>

<script>
import store from '../store/index'

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
            const self = this;
            store.dispatch("login", params).then((response) => {
                    self.resetParameter()
                    self.$router.push("tasks")
                })
        },
        resetParameter(){
            this.email = '',
            this.password = ''
        }
    }
}
</script>