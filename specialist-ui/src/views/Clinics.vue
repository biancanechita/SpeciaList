<template>
  <v-container>
    <ClinicFilterCard
      :autocomplete="queryOptions"
      @filter-updated="handleFilterUpdate"
      class="mb-9"
    ></ClinicFilterCard>
    <v-list
      style="display: flex; flex-wrap: wrap; justify-content: space-around"
    >
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
        class="card-hover"
      >
        <ClinicCard :clinic="item"></ClinicCard>
      </v-list-item>
    </v-list>
  </v-container>
</template>

<script>
import ClinicFilterCard from "@/components/ClinicFilterCard.vue";
import CityClinicsCard from "@/components/CityClinicsCard.vue";
import ClinicCard from "@/components/ClinicCard.vue";
import { clinicsService } from "@/services/clinicsService";

export default {
  data() {
    return {
      items: [],
      queryOptions: {
        filters: {
          cityId: "",
          specializationId: "",
        },
        sortingList: [],
      },
    };
  },
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      clinicsService
        .filterAndSortList(updatedFilterOptions)
        .then((response) => {
          if (response.ok) {
            response
              .text()
              .then((text) => {
                if (text) {
                  this.items = JSON.parse(text);
                  console.log(updatedFilterOptions);
                  console.log(this.items);
                }
              })
              .then(() => {
                this.queryOptions = updatedFilterOptions;
              });
          }
        });
    },
  },
  components: { CityClinicsCard, ClinicFilterCard, ClinicCard },
};
</script>

<style scoped>
.card-hover {
  /* padding: 24px 28px;
  border-radius: 4px;
  box-shadow: 0 12px 12px #0000000d; */
  width: 48.5%;
  margin-bottom: 36px;
}
</style>
