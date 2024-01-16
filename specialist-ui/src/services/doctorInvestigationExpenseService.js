export const doctorInvestigationExpenseService = {
  filterAndSortList,
};

const url = "http://localhost:8089/doctor-investigation-expense";

function filterAndSortList(queryOptions) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(queryOptions),
  };
  return fetch(url + "/filter-and-sort-list", requestOptions);
}
