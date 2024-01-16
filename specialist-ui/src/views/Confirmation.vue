<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-card class="pa-4">
        <v-card-title>Cererea dvs. a fost inregistrata cu succes!</v-card-title>
        <v-card-subtitle
          >Veti fi contactat/a de catre Reteaua de sanatate in legatura cu
          serviciile medicale solicitate.</v-card-subtitle
        >
        <v-card-actions style="padding: 2rem 1rem">
          <v-btn
            color="#e80054"
            theme="dark"
            variant="flat"
            @click="
              () => {
                $router.push({ name: 'Home' });
              }
            "
            >Inapoi la pagina principala</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-row>
  </v-container>
</template>
