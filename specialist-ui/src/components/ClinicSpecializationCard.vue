<template>
  <v-card style="background-color: #f9f9f9" elevation="0">
    <v-card-actions>
      <v-list-item class="w-100">
        <template v-slot:prepend>
          <!-- <v-avatar style="background-color: white;">{{ header }}</v-avatar> -->
          <!-- <v-icon icon="mdi-pill-multiple" color="#73abfe"></v-icon> -->
        </template>

        <v-list-item-title>{{ specialization.specialization }}</v-list-item-title>

        <v-list-item-subtitle>De ce sa ne alegi pe noi?</v-list-item-subtitle>

        <template v-slot:append>
          <div class="justify-self-end">
            <v-btn
              :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
              @click="show = !show"
            ></v-btn>
          </div>
        </template>
      </v-list-item>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show" class="px-4">
        <v-divider></v-divider>
        <v-card-text>
          {{ specialization.description }}
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="ceva">Vezi medici</v-btn>
          <v-btn @click="ceva2">Vezi investigatii</v-btn>
        </v-card-actions>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script>
export default {
  data() {
    return { show: false };
  },
  props: ["specialization", "index"],
  computed: {
    header() {
      return this.index + 1;
    },
  },
  methods: {
    ceva() {
      this.$store.state.queryOptions = {
        filters: {
          cityId: "",
          clinicId: this.$store.state.queryOptions.filters.clinicId,
          fullName: "",
          specializationId: this.specialization.id,
        },
        sortingList: [],
      };

      this.$router.push({
        name: "Doctors",
      });
    },
    ceva2() {
      console.log(this.$store.state.queryOptions.filters.clinicId);

      this.$store.state.queryOptionsInvestigations = {
        filters: {
          id: this.specialization.id,
          clinicId: this.$store.state.queryOptions.filters.clinicId,
        },
        sortingList: [],
      };

      console.log(this.$store.state.queryOptionsInvestigations);

      this.$router.push({
        name: "MedicalInvestigations",
      });
    },
  },
};
</script>
