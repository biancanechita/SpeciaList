import { createStore } from "vuex";

const store = createStore({
  state: {
    token: sessionStorage.getItem("Token") || null,
    queryOptions: {
      filters: { specializationId: "", clinicId: "" },
      sortingList: [],
    },
    queryOptionsAppointments: {
      filters: {
        specializationId: "",
        cityId: "",
        doctorId: "",
      },
      sortingList: [],
    },
    queryOptionsInvestigations: {
      filters: {
        id: "",
        clinicId: "",
      },
      sortingList: [],
    },
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      sessionStorage.setItem("Token", token);
    },
    clearToken(state) {
      state.token = null;
      sessionStorage.removeItem("Token");
    },
  },
  actions: {
    updateToken({ commit }, token) {
      commit("setToken", token);
    },
    logout({ commit }) {
      commit("clearToken");
    },
  },
  getters: {
    getToken(state) {
      return state.token;
    },
  },
});

export default store;
