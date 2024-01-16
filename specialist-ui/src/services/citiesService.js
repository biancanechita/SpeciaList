export const citiesService = {
  filterAndSortList,
  findAll,
  findOne,
};

const url = "http://localhost:8089/cities";

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

function findOne(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id, requestOptions);
}
