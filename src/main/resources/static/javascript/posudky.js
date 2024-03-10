document.addEventListener('DOMContentLoaded', function () {
    var rows = document.querySelectorAll('table tbody tr');

    rows.forEach(function (row, rowIndex) {
        var cells = row.querySelectorAll('td');
        if (row.classList.contains('clenove-row')) {
            cells = row.querySelectorAll('td:not(:last-child)');
        }

        cells.forEach(function (cell, cellIndex) {
            cell.addEventListener('click', function () {
                if (row.classList.contains('clenove-row')) {
                    var idClena =
                        row.querySelector('td:first-child').innerText;
                    window.location.href = '/profil?id=' + idClena;
                    return;
                }
                if (row.classList.contains('sub-row')) {
                    var idUtkani =
                        row.previousElementSibling.querySelector('td:first-child').innerText;
                    window.location.href = '/posudek?idUtkani=' + idUtkani;
                    return;
                }
                var idUtkani = row.querySelector('td:first-child').innerText;
                window.location.href = '/posudek?idUtkani=' + idUtkani;
            });
        });
    });
});

function prejdiNaPosudkyClena(button) {
    // Přesměrování na jinou stránku s využitím ID člena
    window.location.href = '/posudkyClena?id=' + button.value;;
}


document.addEventListener('DOMContentLoaded', function() {
    var searchInput = document.getElementById('searchInput');
    vyhledej(searchInput);
});

function vyhledej(searchInput){
    var rows = document.querySelectorAll('tbody tr');

    if(searchInput == null){
        return;
    }
    searchInput.addEventListener('input', function() {
        var searchTerm = searchInput.value.toLowerCase();

        rows.forEach(function(row) {
            var textContent = row.textContent.toLowerCase();

            if (textContent.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', function() {
    var searchInput = document.getElementById('searchInputClenove');
    if(searchInput != null){
        searchInput.addEventListener('input', function() {
            var radioButtons = document.getElementsByName('jednotkaSkupina');
            var vybranyRb = getVybranaMoznost(radioButtons)
            filtrujTabulku(vybranyRb, tabulka);
        });
    }


    //vyhledej(searchInput);

    var radioButtons = document.getElementsByName('jednotkaSkupina');
    var tabulka = document.getElementById('tabulkaClenu');

    if(radioButtons != null && tabulka != null){
        radioButtons.forEach(function (radioButton) {
            radioButton.addEventListener('change', function () {
                filtrujTabulku(this.value, tabulka);
            });
        });
    }

});

function getVybranaMoznost(radioButtons) {
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            return radioButtons[i].value;
        }
    }
    return 'vse'; // Výchozí hodnota, pokud žádný radio button není vybrán
}

function filtrujTabulku(vybranaMoznost, tabulka) {
    var radky
        = tabulka.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    var hledanyText = document.getElementById('searchInputClenove').value.toLowerCase();
    for (var i = 0; i < radky.length; i++) {
        var jednotka = radky[i].getElementsByTagName('td')[4].textContent;
        var radekText = radky[i].textContent.toLowerCase();
        // Skrýt nebo zobrazit řádek podle vybrané možnosti
        radky[i].style.display =
            (jednotka === vybranaMoznost || vybranaMoznost === 'vse') &&
            (radekText.includes(hledanyText) || hledanyText === '') ? '' : 'none';
    }
}

function showResult(element, result) {
    var result = element.getAttribute('rZnamka');
    element.innerHTML = result;
    element.style.color = "yellow";
}

function hideResult(element, result) {
    var result = element.getAttribute('rId');
    element.innerHTML = result;
    element.style.color = "white";
}

function showAdditionalRow(element) {
    if (element.classList.contains('sub-row')) {
       // element.previousElementSibling.style.background = "black";
        element.style.display = "table-row";
        return;
    }
    var additionalRow = element.nextElementSibling;
    additionalRow.style.display = "table-row";
}

function hideAdditionalRow(element) {
    if (element.classList.contains('sub-row')) {
      /*  if(element.previousElementSibling.classList.contains('even-row')){
            element.previousElementSibling.style.background = "#22222C";
        } else {
            element.previousElementSibling.style.background = "#3f4a41";
        }*/
        element.style.display = "none";
        return;
    }
    var additionalRow = element.nextElementSibling;
    additionalRow.style.display = "none";
}

function vratSeNaPredchoziStranku() {
    history.back();
}

function vratSeNaLogin() {
    window.location.href = '/';
}


function presunNaZmenuHesla() {
    window.location.href = '/zmena_hesla';
}