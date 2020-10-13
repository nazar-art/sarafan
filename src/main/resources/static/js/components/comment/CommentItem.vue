<!--<template>
    <v-list-tile>
        <v-list-tile-avatar>
            <img :src="comment.author.userpic">
        </v-list-tile-avatar>

        <v-list-tile-content>
            <v-list-tile-title>{{comment.text}}</v-list-tile-title>
            <v-list-tile-sub-title>{{comment.author.name}}</v-list-tile-sub-title>
        </v-list-tile-content>
    </v-list-tile>
</template>-->

<template>
    <v-list-item>
        <user-link
                :user="comment.author"
        ></user-link>

        <v-list-item-content>
            <v-list-item-title>{{comment.text}}</v-list-item-title>
        </v-list-item-content>
    </v-list-item>
</template>

<script>
    import UserLink from 'components/UserLink.vue'

    export default {
        name: 'CommentItem',
        components: {UserLink},
        props: ['comment']
    }
</script>

<style scoped>
</style>