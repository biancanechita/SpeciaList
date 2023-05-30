<template>
  <v-container>
    <DoctorFilterCard @query-options="handleQueryOptions"></DoctorFilterCard>
    <v-list lines="one" style="background-color: #f7f7f7">
      <v-list-item v-for="(item, index) in items" :key="index" class="px-0"
        ><DoctorCard :doctor="item"></DoctorCard
      ></v-list-item>
    </v-list>
  </v-container>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import DoctorCard from "@/components/DoctorCard.vue";
import DoctorFilterCard from "@/components/DoctorFilterCard.vue";

export default {
  data() {
    return {
      items: [],
      queryOptions: {},
      queryOptions: {},
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
    filterAndSortList() {
      doctorService.filterAndSortList(this.queryOptions).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.items = JSON.parse(text);
            }
          });
        }
      });
    },
    handleQueryOptions(queryOptions) {
      this.queryOptions = queryOptions;
      this.filterAndSortList();
    },
  },
  created() {
    this.findAll();
  },
  components: { DoctorCard, DoctorFilterCard },
};
</script>
