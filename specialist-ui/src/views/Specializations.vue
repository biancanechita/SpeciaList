<template>
  <v-container>
    <SpecializationFilterCard
      @filter-updated="handleFilterUpdate"
      class="mb-9"
    ></SpecializationFilterCard>
    <v-list
      style="display: flex; flex-wrap: wrap; justify-content: space-around"
    >
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
        style="width: 48.5%; margin-bottom: 36px"
      >
        <SpecializationCard
          :specialization="item"
          :index="index"
        ></SpecializationCard>
      </v-list-item>
    </v-list>
  </v-container>
</template>

<script>
import { specializationService } from "@/services/specializationService";
import SpecializationCard from "@/components/SpecializationCard.vue";
import SpecializationFilterCard from "@/components/SpecializationFilterCard.vue";

export default {
  data() {
    return {
      items: [],
    };
  },
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      specializationService
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
  components: { SpecializationCard, SpecializationFilterCard },
};
</script>
