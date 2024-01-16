<template>
  <v-card elevation="0">
    <v-card-actions class="py-0">
      <v-list-subheader class="pl-4">Filter</v-list-subheader>
      <v-text-field
        v-model="specialization"
        placeholder="Enter keyword"
        append-inner-icon="mdi-magnify"
        variant="outlined"
        color="#e80054"
        :hide-details="true"
        style="padding: 12px 24px"
      ></v-text-field>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return { specialization: "" };
  },
  methods: {
    onClear() {
      this.specialization = "";
    },
  },
  watch: {
    specialization: {
      handler() {
        const filterOptions = {
          filters: {
            clinicId: this.$store.state.queryOptions.filters.clinicId,
            specialization: this.specialization,
          },
          sortingList: [],
        };
        console.log(filterOptions);
        this.$emit("filter-updated", filterOptions);
      },
      immediate: true,
    },
  },
  emits: ["filter-updated"],
};
</script>
