export const citiesService = {
  findAll,
};

const url = "http://localhost:8080/cities";

function findAll() {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url, requestOptions);
}
