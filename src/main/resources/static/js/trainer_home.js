function updateWeek(selected) {
    var tableDetails = document.getElementById("table-details");
    var tables = tableDetails.getElementsByTagName("TABLE");
    var i;

    for (i = 0; i < tables.length; i++) {
        tables[i].hidden = true;
    }
    document.getElementById('traineeTable' + selected.value).hidden = false;
}
function rowClicked(value) {
    location.href = "/feedback?id=" + value;
}