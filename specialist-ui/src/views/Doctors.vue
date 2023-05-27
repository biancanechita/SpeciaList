<template>
  <v-list lines="one">
    <v-list-item v-for="(item, index) in items" :key="index"
      ><DoctorCard :doctor="item"></DoctorCard
    ></v-list-item>
  </v-list>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import DoctorCard from "@/components/DoctorCard.vue";

export default {
  data() {
    return {
      items: [],
    };
  },
  methods: {
    findAll() {
      doctorService.findAll().then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.items = JSON.parse(text);
            }
          });
        }
      });
    },
  },
  created() {
    this.findAll();
  },
  components: { DoctorCard },
};
</script>
