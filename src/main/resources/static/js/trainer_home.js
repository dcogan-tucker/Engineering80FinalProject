function updateWeek(selected) {
    var tableDetails = document.getElementById("table-details");
    var tables = tableDetails.getElementsByTagName("TABLE");
    var i = 0;

    for (i = 0; i < tables.length; i++) {
        tables[i].hidden = true;
    }

    document.getElementById('traineeTable' + selected.value).hidden = false;
    var button = document.getElementById('updateWeek' + i);

    if (i == selected.value) {
        button.hidden = false;
    } else {
        button.hidden = true;
    }
}

function rowClicked(value) {
    location.href = "/feedback?id=" + value;
}