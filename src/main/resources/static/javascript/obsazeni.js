document.getElementById('toggleSidebar').addEventListener('click', function() {
    var sidebar = document.getElementById('sidebar');
    var toggleSidebar = document.getElementById('toggleSidebar');
    if (sidebar.style.right === '0px') {
        sidebar.style.right = '-400px';
        toggleSidebar.classList.remove('arrow-right');
        toggleSidebar.classList.add('arrow-left');
    } else {
        sidebar.style.right = '0px';
        toggleSidebar.classList.remove('arrow-left');
        toggleSidebar.classList.add('arrow-right');
    }
});

document.addEventListener('DOMContentLoaded', function () {
    var obsazeniUtkani = document.querySelector('.obsazeniUtkani');
    var obsazeniPodrobnosti = document.querySelector('.obsazeniPodrobnostiPomocny');
    var radekPodrobnosti = document.querySelectorAll('.radekPodrobnosti');

    obsazeniUtkani.addEventListener('click', function () {
        obsazeniPodrobnosti.classList.toggle('hidden');
        radekPodrobnosti.forEach(function (radek) {
            radek.classList.toggle('hidden');
        });
    });
});

const tooltipItems = document.querySelectorAll('.clenove-row');
const infoSidebar = document.getElementById('infoSidebar');
const infoContent = document.getElementById('infoContent');

tooltipItems.forEach(item => {
    item.addEventListener('mouseover', () => {
        const info = getInfoFromRow(item); // Zde získáte informace z aktuálního řádku
        updateInfoSidebar(info);
        infoSidebar.classList.add('active');
    });

    item.addEventListener('mouseleave', () => {
        infoSidebar.classList.remove('active');
    });
});

function getInfoFromRow(row) {
    // Zde získejte potřebné informace z řádku tabulky
    // Například:
    const id = row.querySelector('td:nth-child(1)').innerText;
    const jmeno = row.querySelector('td:nth-child(2)').innerText;
    const jednotka = row.querySelector('td:nth-child(3)').innerText;

    return { id, jmeno, jednotka };
}

function updateInfoSidebar(info) {
    // Zde aktualizujte obsah lišty s novými informacemi
    infoContent.innerHTML = `
            <p>ID: ${info.id}</p>
            <p>Jméno: ${info.jmeno}</p>
            <p>Jednotka: ${info.jednotka}</p>
            <!-- Přidejte další informace podle potřeby -->
        `;
}