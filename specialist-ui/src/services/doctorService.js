export const doctorService = {
  filterAndSortList,
  findAll,
  findById,
  save,
  getFullName,
};

const url = "http://localhost:8089/doctors";

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

function findById(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id, requestOptions);
}

function save(doctor) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(doctor),
  };
  return fetch(url, requestOptions);
}

function getFullName(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id + "/get-full-name", requestOptions);
}
