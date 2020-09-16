function sortCars() {
    let price = document.getElementById("price").value;
    return car => car.price < price;

}

function sortByPrice() {
    var filteredCars = cars.filter(sortCars())
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

function sortByMake() {

    let make = document.getElementById("make").value;
    let filteredCars = cars.filter(car => car.make === make);
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
