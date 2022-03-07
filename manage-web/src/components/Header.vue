<template>
    <v-app-bar dark app height="70">
        <v-toolbar-title><router-link tag="div" to="/">Manage-app</router-link></v-toolbar-title>
        <v-spacer></v-spacer>
        <div v-if="isLoggedIn()">
            <v-menu>
                <template v-slot:activator="{ on }">
                    <v-btn v-on="on">{{ getName() }}</v-btn>
                </template>
                <v-list>
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title><div @click="logout()">ログアウト</div></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-menu>
        </div> 
    </v-app-bar>
</template>

<script>
import store from '../store/index'

export default{
    name: 'headers',
    methods: {
        logout(){
            localStorage.removeItem("JWTToken");
            store.commit("onLogoutSuccess")
            this.$router.push("/")
        },
        isLoggedIn(){
            return store.state.user.isLoggedIn
        },
        getName(){
            return store.getters.getToken.name
        }
    },
}
</script>

<style scoped>
div:hover {
    cursor: pointer;
}
</style>