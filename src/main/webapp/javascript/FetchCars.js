

let getFetchButtonCars = document.getElementById("fetchCars");

getFetchButtonCars.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllCars();
});

function fetchAllCars() {
    // let price = document.getElementById("valueOfPrice").value
    let url = "http://localhost:8080/jpareststarter/api/groupmembers/all/";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let allCars = document.getElementById("mybody");
                let result = '';
                data.forEach((x) => {
                    result += getTableRow(x)
                });
                allCars.innerHTML = result;
            });
}

function getTableRow(car) {
    return `<tr><td>${car.id}</td><td>${car.make}</td><td>${car.model}</td><td>${car.price}</td><td>${car.year}</td></tr>`;
}

function SortByPrice() {

    var filteredCars = document.getElementById("#table").rows.length

    html = filteredCars.map(car =>
        "<tr><td>" + car.id + "</td>\n\
                       <td>" + car.year + "</td>\n\
                       <td>" + car.make + "</td>\n\
                       <td>" + car.model + "</td>\n\
                       <td>" + car.price + "</td>\n\
                       <td>")

    document.getElementById("mybody").innerHTML = html;
    event.preventDefault();
}
