

/* global then */

let getFetchButton = document.getElementById("fetchButton");

getFetchButton.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllMembers();
});

function fetchAllMembers() {
    //let url = "https://https://kodekongen.dk/CA1_Opgave/api/groupmembers/all";
    let url = "http://localhost:8080/jpareststarter/api/groupmembers/all/";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let allMembers = document.getElementById("tbody");
                let result = '';
                data.forEach((x) => {
                    result += getTableRow(x)
                });
                allMembers.innerHTML = result
            });
}
/*
 function getTableHeader() {
 return '<thead><th>Name</th><th>StudentId</th><th>Favorite Series</th></thead>';
 }
 * 
 */


function getTableRow(member) {
    return `<tr><td>${member.name}</td><td>${member.studentId}</td><td>${member.favoriteSeries}</td></tr>`;
}