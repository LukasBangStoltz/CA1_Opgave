

let getFetchButtonCars = document.getElementById("fetchCars");
let getFetchButtonSortPrice = document.getElementById("SortByPrice");
let getFetchButtonSortMake = document.getElementById("SortByMake");
let getFetchButtonFilterMake = document.getElementById("FilterByMake");

getFetchButtonFilterMake.addEventListener('click', (event) => {
    event.preventDefault();
    filterByMake();
});

getFetchButtonCars.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllCars();
});

getFetchButtonSortPrice.addEventListener('click', (event) => {
    event.preventDefault();
    SortByPrice();
})

getFetchButtonSortMake.addEventListener('click', (event) => {
    event.preventDefault();
    SortByMake();
})

function fetchAllCars() {
    // let price = document.getElementById("valueOfPrice").value
    //let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    let url = "http://localhost:8080/jpareststarter/api/car/all/"
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


function SortByPrice() {
    
    //let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    let url = "http://localhost:8080/jpareststarter/api/car/all/"
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let sortArray = data.sort((b,a) => a.price-b.price);
                
                let allCars = document.getElementById("mybody");
                let result = '';
                sortArray.forEach((x) => {
                    result += getTableRow(x)
                });
                allCars.innerHTML = result;
            });
}

function SortByMake() {
    
    //let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    let url = "http://localhost:8080/jpareststarter/api/car/all/"
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let sortArray = data.sort((b,a) => a.make < b.make);
                let allCars = document.getElementById("mybody");
                let result = '';
                sortArray.forEach((x) => {
                    result += getTableRow(x)
                });
                allCars.innerHTML = result;
            });
}


function filterByMake() {
    
    //let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    let url = "http://localhost:8080/jpareststarter/api/car/all/"
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let filterArray = data.filter((b,a) => a.make < b.make);
                let ulist = document.getElementById("ulist");
                let html = "";
                filterArray.forEach((x) => {
                    html += "<li>" + x + "</li>";
                });
                let htmlString = "<ul>" + html.join('') + "</ul>";
                ulist.innerHTML = htmlString;
            });
}

function getTableRow(car) {
    return `<tr><td>${car.id}</td><td>${car.year}</td><td>${car.make}</td><td>${car.model}</td><td>${car.price}</td></tr>`;
}