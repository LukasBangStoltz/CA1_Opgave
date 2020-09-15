/* global then */

let getFetchButtonJokes = document.getElementById("fetchButtonJokes");
let getFetchButtonSingleJoke = document.getElementById("fetchButtonSingleJoke");

getFetchButtonJokes.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllJokes();
});

getFetchButtonSingleJoke.addEventListener('click', (event)=> {
   event.preventDefault();
   fetchSingleJoke();
});

function fetchAllJokes() {
    let url = "http://localhost:8080/jpareststarter/api/jokes/alljokes/";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let allJokes = document.getElementById("tbodyJokes");
                let result = '';
                data.forEach((x) => {
                    result += getTableRow(x)
                });
                allJokes.innerHTML = result
            });
}

function fetchSingleJoke() {
    let id = document.getElementById("singleJokeId").value;
    let url = "http://localhost:8080/jpareststarter/api/jokes/id/" + id;
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let singleJoke = document.getElementById("tbodyJokes");
                let result = '';
                result = getTableRow(data)
                singleJoke.innerHTML = result
            });
}

function getTableRow(joke) {
    return `<tr><td>${joke.id}</td><td>${joke.theJoke}</td><td>${joke.jokeType}</td></tr>`;
}