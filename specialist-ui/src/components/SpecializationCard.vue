<template>
  <v-card
    style="padding: 24px 28px; border: 1px solid rgba(60, 60, 60, 0.12)"
    elevation="0"
  >
    <v-card-title class="title-hover">{{
      specialization.specialization
    }}</v-card-title>
    <v-card-text>{{ specialization.description }} </v-card-text>
    <v-card-actions>
      <v-btn color="#e80054" variant="flat" theme="dark" @click="ceva"
        >Vezi medici</v-btn
      >
      <v-btn color="#f1f1f1" variant="flat" @click="ceva2"
        >Vezi investigatii</v-btn
      >
    </v-card-actions>
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
