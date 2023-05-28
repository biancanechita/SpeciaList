<template>
  <div class="d-flex flex-column align-center">
    <v-list lines="one" style="background-color: #f7f7f7">
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
        style="background-color: #f7f7f7"
      >
        <SpecializationCard :specialization="item"></SpecializationCard>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { specializationService } from "@/services/specializationService";
import SpecializationCard from "@/components/SpecializationCard.vue";

export default {
  data() {
    return {
      items: [],
    };
  },
  methods: {
    findAll() {
      specializationService.findAll().then((response) => {
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
  components: { SpecializationCard },
};
</script>
