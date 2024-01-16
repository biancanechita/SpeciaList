<template>
  <v-container>
    <DoctorFilterCard
      :autocomplete="$store.state.queryOptions"
      @filter-updated="handleFilterUpdate"
      class="mb-9"
    ></DoctorFilterCard>
    <v-list style="padding: 16px">
      <v-list-item v-for="(item, index) in items" :key="index"
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
    };
  },
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      doctorService.filterAndSortList(updatedFilterOptions).then((response) => {
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
  components: { DoctorCard, DoctorFilterCard },
};
</script>
