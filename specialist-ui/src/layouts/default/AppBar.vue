<template>
  <v-app-bar
    flat
    density="comfortable"
    style="border-bottom: 1px solid rgba(60, 60, 60, 0.12); padding: 0 32px"
  >
    <v-app-bar-title style="font-size: 16px; font-weight: 500"
      >Platforma pentru Sănătate</v-app-bar-title
    >

    <v-spacer></v-spacer>

    <v-list style="display: flex; padding: 0">
      <v-list-item
        v-for="item in items"
        :key="item.value"
        style="padding: 0 12px"
      >
        <a @click="handleItemClick(item.value)">
          <p class="link-text">{{ item.title }}</p>
        </a>
      </v-list-item>
      <v-list-item style="padding: 0 12px" v-if="login"
        ><a @click="handleItemClick('UserProfilePage')">
          <p class="link-text">Contul meu</p>
        </a></v-list-item
      >
    </v-list>

    <v-btn v-if="login" @click="signOut">Deconectați-vă</v-btn>
    <v-btn v-else @click="handleItemClick('LoginPage')">Intră in cont</v-btn>
  </v-app-bar>
</template>

<script>
export default {
  data() {
    return {
      login: false,
      items: [
        {
          title: "Noutati",
          value: "Home",
        },
        { title: "Medici", value: "Doctors" },
        {
          title: "Locatii",
          value: "Clinics",
        },
        {
          title: "Specialitati",
          value: "Specializations",
        },
        {
          title: "Programari",
          value: "AppointmentsDashboard",
        },
      ],
    };
  },
  computed: {
    token() {
      return this.$store.getters.getToken;
    },
  },
  methods: {
    handleItemClick(value) {
      // Perform action based on the value of the clicked item
      this.$router.push({ name: value });
    },
    signOut() {
      sessionStorage.clear();
      this.$store.dispatch("updateToken", "");
      this.login = false;
    },
  },
  watch: {
    token(newToken) {
      // Verificați dacă token-ul s-a schimbat și aplicați logica corespunzătoare
      console.log("Token has changed:", newToken);
    },
  },
  created() {
    this.login = sessionStorage.getItem("Token") !== "";
  },
};
</script>

<style scoped>
.link-text {
  font-family: "Inter var experimental", "Inter var", Inter, -apple-system,
    BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell,
    "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
  font-size: 13px;
  font-weight: 500;
  color: #213547;
}
</style>
