document.addEventListener('DOMContentLoaded', function () {
    var rows = document.querySelectorAll('table tbody tr');

    rows.forEach(function (row, index) {
        row.addEventListener('click', function () {
            var idUtkani = this.querySelector('td:first-child').innerText;
            window.location.href = '/posudek?idUtkani=' + idUtkani;
        });
    });
});