<template>
  <v-container class="fill-height">
    <v-row style="padding: 48px 32px; height: 100%">
      <v-row justify="end">
        <v-avatar image="@/assets/doctor.png" size="180"></v-avatar>
        <h1
          class="font-weight-bold"
          style="
            text-align: start;
            font-size: 76px;
            line-height: 1.25;
            font-weight: 900;
            letter-spacing: -1.5px;
            color: #213547;
          "
        >
          Platforma pentru Sănătate și Binele Tău
        </h1>
      </v-row>

      <v-row
        align="start"
        class="font-weight-light"
        style="font-size: 22px; padding: 24px 40px; text-align: center"
      >
        <v-row
          style="display: flex; justify-content: center; align-items: center"
        >
          <v-btn color="#213547" size="large" variant="text" :disabled="true">
            Explorează serviciile noastre
          </v-btn>
          <v-btn
            color="#e80054"
            @click="
              () => {
                this.$router.push({ name: 'LoginPage' });
              }
            "
            size="large"
            variant="flat"
            theme="dark"
            prepend-icon="mdi-login-variant"
            v-if="!login"
          >
            Autentificare
          </v-btn>
        </v-row>

        <v-row align="end">
          O platformă online cuprinzătoare pentru căutarea de medici și
          programarea de consultații în cadrul unei rețele medicale la nivel
          național
        </v-row>
      </v-row>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      login: false,
    };
  },
  computed: {
    token() {
      return this.$store.getters.getToken;
    },
  },
  watch: {
    token(newToken) {
      console.log("Token has changed:", newToken);
      this.login = !!newToken;
    },
  },
  created() {
    this.login = !!sessionStorage.getItem("Token");
  },
};
</script>
