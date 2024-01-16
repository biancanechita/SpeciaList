<template>
  <v-container fluid>
    <v-navigation-drawer>
      <v-list style="padding: 24px 32px 96px">
        <v-list-item v-for="(item, index) in items" :key="index">
          <a @click="goToPage(item.value)">
            <p class="link-text">{{ item.title }}</p></a
          >
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <router-view class="pa-0"></router-view>
  </v-container>
</template>

<script>
import { clinicsService } from "@/services/clinicsService";

export default {
  data() {
    return {
      items: [
        {
          title: "Despre",
          value: "ClinicProfile",
        },
        {
          title: "Medici",
          value: "ClinicDoctors",
        },
        {
          title: "Specialitati",
          value: "ClinicSpecializations",
        },
      ],

      clinic: {},
    };
  },
  methods: {
    findById() {
      clinicsService.findById(this.$route.params.id).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.clinic = JSON.parse(text);
            }
          });
        }
      });
    },
    goToPage(value) {
      this.$store.state.queryOptions = {
        filters: {
          cityId: "",
          clinicId: this.clinic.id,
          fullName: "",
          specializationId: "",
        },
        sortingList: [],
      };

      console.log(this.$store.state.queryOptions);

      this.$router.push({
        name: value,
      });
    },
  },
  created() {
    this.findById();
  },
};
</script>

<style scoped>
.link-text {
  line-height: 20px;
  font-size: 13px;
  font-weight: 500;
  color: rgba(60, 60, 60, 0.7);
  transition: color 0.5s;
}
.link-text:hover {
  color: #213547;
}
</style>
