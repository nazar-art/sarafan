import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store'
import { connect } from './util/ws'
import 'vuetify/dist/vuetify.min.css'
/*import * as Sentry from '@sentry/browser'
import * as Integrations from '@sentry/integrations'

Sentry.init({
    dsn: "https://8d692c4e034c498d9d6d215f3fbfc8e9@o468493.ingest.sentry.io/5496421",
    integrations: [
        new Integrations.Vue({
            Vue,
            attachProps: true,
        }),
    ],
})*/

import * as Sentry from "@sentry/browser";
import { Vue as VueIntegration } from "@sentry/integrations";
import { Integrations } from "@sentry/tracing";

Sentry.init({
    dsn: "https://8d692c4e034c498d9d6d215f3fbfc8e9@o468493.ingest.sentry.io/5496421",
    integrations: [
        new VueIntegration({
            Vue,
            tracing: true,
        }),
        new Integrations.BrowserTracing(),
    ],

    // We recommend adjusting this value in production, or using tracesSampler
    // for finer control
    tracesSampleRate: 1.0,
});


Sentry.configureScope(scope =>
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name
    })
)

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})