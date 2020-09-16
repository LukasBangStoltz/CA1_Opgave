

/* global then */

let getFetchButtonMembers = document.getElementById("fetchButtonMembers");

getFetchButtonMembers.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllMembers();
});

function fetchAllMembers() {
    //let url = "https://https://kodekongen.dk/CA1_Opgave/api/groupmembers/all";
    let url = "http://localhost:8080/jpareststarter/api/groupmembers/all/";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let allMembers = document.getElementById("tbodyMembers");
                let result = '';
                data.forEach((x) => {
                    result += getTableRow(x)
                });
                allMembers.innerHTML = result
            });
}

function getTableRow(member) {
    return `<tr><td>${member.name}</td><td>${member.studentId}</td><td>${member.favoriteSeries}</td></tr>`;
}