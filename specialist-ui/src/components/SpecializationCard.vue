<template>
  <v-card
    variant="text"
    style="background-color: white; padding: 16px"
    width="912"
  >
    <div class="d-inline-flex" style="width: 100%">
      <div class="d-flex align-center" style="width: 100%">
        <v-avatar class="ma-3" size="60">
          <v-img
            src="https://cdn-icons-png.flaticon.com/512/249/249217.png"
          ></v-img>
        </v-avatar>
        <div style="width: 100%">
          <v-card-title>{{ specialization.name }}</v-card-title>

          <v-expand-transition>
            <div v-show="show" style="padding-top: 18px">
              <div style="padding: 20.4px 0">
                <a
                  href=""
                  class="text-decoration-none text-subtitle-1"
                  style="padding: 0.5rem 1rem; color: #ff314f"
                  >Vezi medici</a
                >
              </div>

              <v-divider></v-divider>

              <div style="padding: 20.4px 0">
                <a
                  href=""
                  class="text-decoration-none text-subtitle-1"
                  style="padding: 0.5rem 1rem; color: #ff314f"
                  >Vezi investigatii</a
                >
              </div>
            </div>
          </v-expand-transition>
        </div>
      </div>

      <v-spacer></v-spacer>

      <v-card-actions>
        <v-btn
          :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          elevation="0"
          @click="show = !show"
        ></v-btn>
      </v-card-actions>
    </div>
  </v-card>
</template>

<script>
export default {
  data() {
    return { show: false };
  },
  props: ["specialization"],
};
</script>
