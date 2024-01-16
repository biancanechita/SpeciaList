<template>
  <v-list>
    <v-list-item
      v-for="(item, index) in items"
      :key="index"
      class="my-2 ceva pa-0"
      style="border-radius: 4px"
    >
      <ClinicCard :clinic="item"></ClinicCard>
    </v-list-item>
  </v-list>
</template>

<script>
import { clinicsService } from "@/services/clinicsService";
import ClinicCard from "./ClinicCard.vue";

export default {
  data() {
    return {
      items: [],
    };
  },
  props: ["city", "initialQueryOptions"],
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      clinicsService
        .filterAndSortList(updatedFilterOptions)
        .then((response) => {
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
  watch: {
    initialQueryOptions: {
      handler() {
        const updatedFilterOptions = {
          filters: {
            cityId: this.city.id,
            specializationId: this.initialQueryOptions.filters.specializationId,
          },
          sortingList: [],
        };

        this.handleFilterUpdate(updatedFilterOptions);
      },
      deep: true,
    },
  },
  components: { ClinicCard },
};
</script>

<style scoped>
/* .ceva {
  box-shadow: 0 12px 12px #0000000d;
  transition: background-color 0.5s, box-shadow 0.25s ease,
    border-color 0.25s ease;
} */
.ceva:hover {
  box-shadow: 0 12px 12px #0000001a;
}
</style>
