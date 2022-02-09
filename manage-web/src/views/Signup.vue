<template>
    <div class="container">
        <h1 >サインアップ</h1>
        <v-text-field label="名前" v-model="name"></v-text-field>
        <v-text-field label="メールアドレス" v-model="email"></v-text-field>
        <v-text-field label="パスワード" type="password" v-model="password"></v-text-field>

        <v-btn @click="signUp()">Sign up</v-btn>
    </div>
</template>

<script>
import store from '../store/index'


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
            const self = this;
            store.dispatch("signup", params).then(() => {
                self.$router.push("login")
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