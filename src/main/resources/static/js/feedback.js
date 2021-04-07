function toggleUser(enabled, disabled) {
    var divEnabled = document.getElementById(enabled);
    var divDisabled = document.getElementById(disabled);
    divEnabled.hidden = false;
    divDisabled.hidden = true;
}

function toggleTextBox(enabled, disabled, disabled2) {
    var textboxEnabled = document.getElementById(enabled);
    var textboxDisabled = document.getElementById(disabled);
    var textboxDisabled2 = document.getElementById(disabled2);
    textboxEnabled.hidden = false;
    textboxDisabled.hidden = true;
    textboxDisabled2.hidden = true;
}