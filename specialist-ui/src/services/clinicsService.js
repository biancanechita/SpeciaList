export const clinicsService = {
  filterAndSortList,
  findAll,
  findByCityId,
  findById,
};

const url = "http://localhost:8089/clinics";

function filterAndSortList(queryOptions) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(queryOptions),
  };
  return fetch(url + "/filter-and-sort-list", requestOptions);
}

function findAll() {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url, requestOptions);
}

function findByCityId(cityId) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/city/" + cityId, requestOptions);
}

function findById(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id, requestOptions);
}
