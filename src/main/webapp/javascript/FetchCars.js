

let getFetchButtonCars = document.getElementById("fetchCars");
let getFetchButtonSortPrice = document.getElementById("SortByPrice");
let getFetchButtonSortMake = document.getElementById("SortByMake");
let getFetchButtonFilterMake = document.getElementById("FilterByMake");
let getFetchButtonFilterYear = document.getElementById("FilterByYear");

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

getFetchButtonFilterYear.addEventListener('click', (event) =>{
    event.preventDefault();
    filterByYear();
})




function fetchAllCars() {
    // let price = document.getElementById("valueOfPrice").value
    let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    //let url = "http://localhost:8080/jpareststarter/api/car/all/"
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

    let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    //let url = "http://localhost:8080/jpareststarter/api/car/all/"
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let sortArray = data.sort((b, a) => a.price - b.price);

                let allCars = document.getElementById("mybody");
                let result = '';
                sortArray.forEach((x) => {
                    result += getTableRow(x)
                });
                allCars.innerHTML = result;
            });
}

function SortByMake() {

    let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    //let url = "http://localhost:8080/jpareststarter/api/car/all/"
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let sortArray = data.sort((a,b)=> (a.make > b.make)*2-1);
                let allCars = document.getElementById("mybody");
                let result = '';
                sortArray.forEach((x) => {
                    result += getTableRow(x)
                });
                allCars.innerHTML = result;
            });
}


function filterByMake() {

    let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    //let url = "http://localhost:8080/jpareststarter/api/car/all/"
    
    
    fetch(url)
            .then(res => res.json())
            .then(data => {
                
                let typedMake = document.getElementById("filterMake").value;
        
                let filterArray = data.filter(n=> n.make === typedMake );
       
                let allCars = document.getElementById("mybody");
                let result = '';
                filterArray.forEach((x) => {
                    result += getTableRow(x);
                });
                allCars.innerHTML = result;
            });
}


function filterByYear() {

    let url = "https://kodekongen.dk/CA1_Opgave/api/car/all";
    //let url = "http://localhost:8080/jpareststarter/api/car/all/"
    
    
    fetch(url)
            .then(res => res.json())
            .then(data => {
                
                let typedYear = document.getElementById("filterYear").value;
        
                let filterArray = data.filter(n=> n.year <= typedYear );
       
                let allCars = document.getElementById("mybody");
                let result = '';
                filterArray.forEach((x) => {
                    result += getTableRow(x);
                });
                allCars.innerHTML = result;
            });
}


function getTableRow(car) {
    return `<tr><td>${car.id}</td><td>${car.year}</td><td>${car.make}</td><td>${car.model}</td><td>${car.price}</td></tr>`;
}