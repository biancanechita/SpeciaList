export const clinicsService = { findByCityId };

const url = "http://localhost:8080/clinics";

function findByCityId(cityId) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/city/" + cityId, requestOptions);
}
