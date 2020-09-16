/* global then */

let getFetchButtonJokes = document.getElementById("fetchButtonJokes");
let getFetchButtonSingleJoke = document.getElementById("fetchButtonSingleJoke");
let getFetchButtonRandomJoke = document.getElementById("fetchButtonRandomJokes")


getFetchButtonJokes.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllJokes();
});

getFetchButtonSingleJoke.addEventListener('click', (event) => {
    event.preventDefault();
    fetchSingleJoke();
});

getFetchButtonRandomJoke.addEventListener('click', (event) => {
    event.preventDefault()
    fetchRandomJoke();
})

function fetchAllJokes() {
    let url = "http://localhost:8080/jpareststarter/api/jokes/alljokes/";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let jokeTableBody = document.getElementById("tbodyJokes");
                let result = '';
                data.forEach((x) => {
                    result += getTableRow(x)
                });
                jokeTableBody.innerHTML = result
            });
}

function fetchSingleJoke() {

    let id = document.getElementById("singleJokeId").value;
    if (id < 11) {


        let url = "http://localhost:8080/jpareststarter/api/jokes/id/" + id;
        fetch(url)
                .then(res => res.json())
                .then(data => {
                    let jokeTableBody = document.getElementById("tbodyJokes");
                    let result = '';
                    result = getTableRow(data)
                    jokeTableBody.innerHTML = result
                });
    } else {
        let jokeTableBody = document.getElementById("tbodyJokes");
        jokeTableBody.innerHTML = "Der er ingen joke, med dit givne ID"
    }




}


function fetchRandomJoke() {
    let url = "http://localhost:8080/jpareststarter/api/jokes/randomjoke/"
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let jokeTableBody = document.getElementById("tbodyJokes");
                let result = getTableRow(data)
                jokeTableBody.innerHTML = result;
            });
}


function getTableRow(joke) {
    return `<tr><td>${joke.id}</td><td>${joke.theJoke}</td><td>${joke.jokeType}</td></tr>`;
}