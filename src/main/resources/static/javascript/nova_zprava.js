const polocas1 = document.getElementById('nastaveniPrvniPolocas');
const polocas2 = document.getElementById('nastaveniDruhyPolocas');
const polocas = document.getElementById('polocas');
const vysledek = document.getElementById('vysledek');
const znamkaRHCH = document.getElementById('znamkaRHCH');
const znamkaAR1HCH = document.getElementById('znamkaAR1HCH');
const znamkaAR2HCH = document.getElementById('znamkaAR2HCH');
const listaR = document.getElementById('listaR');
const listaR1 = document.getElementById('listaR1');
const listaR2 = document.getElementById('listaR2');
const listaR3 = document.getElementById('listaR3');
const listaR4 = document.getElementById('listaR4');
const listaAR1 = document.getElementById('listaAR1');
const listaAR1_1 = document.getElementById('listaAR1_1');
const listaAR1_2 = document.getElementById('listaAR1_2');
const listaAR2 = document.getElementById('listaAR2');
const listaAR2_1 = document.getElementById('listaAR2_1');
const listaAR2_2 = document.getElementById('listaAR2_2');
const listaOstatni = document.getElementById('listaOstatni');
const listaOstatni1 = document.getElementById('listaOstatni1');
const listaOstatni2 = document.getElementById('listaOstatni2');
const listaOstatni3 = document.getElementById('listaOstatni3');
const listaSituace = document.getElementById('listaSituace');
const listaRJmeno = document.getElementById('listaRJmeno');
const listaAR1Jmeno = document.getElementById('listaAR1Jmeno');
const listaAR2Jmeno = document.getElementById('listaAR2Jmeno');

const blokR = document.getElementById('blokR');
const blokR1 = document.getElementById('blokR1');
const blokR2 = document.getElementById('blokR2');
const blokR3 = document.getElementById('blokR3');
const blokR4 = document.getElementById('blokR4');
const blokAR1 = document.getElementById('blokAR1');
const blokAR1_1 = document.getElementById('blokAR1_1');
const blokAR1_2 = document.getElementById('blokAR1_2');
const blokAR2 = document.getElementById('blokAR2');
const blokAR2_1 = document.getElementById('blokAR2_1');
const blokAR2_2 = document.getElementById('blokAR2_2');
const blokOstatni = document.getElementById('blokOstatni');
const blokOstatni1 = document.getElementById('blokOstatni1');
const blokOstatni2 = document.getElementById('blokOstatni2');
const blokOstatni3 = document.getElementById('blokOstatni3');
const blokSituace = document.getElementById('blokSituace');

const sipkaR = document.getElementById('sipkaR');
const sipkaR1 = document.getElementById('sipkaR1');
const sipkaR2 = document.getElementById('sipkaR2');
const sipkaR3 = document.getElementById('sipkaR3');
const sipkaR4 = document.getElementById('sipkaR4');
const sipkaAR1 = document.getElementById('sipkaAR1');
const sipkaAR1_1 = document.getElementById('sipkaAR1_1');
const sipkaAR1_2 = document.getElementById('sipkaAR1_2');
const sipkaAR2 = document.getElementById('sipkaAR2');
const sipkaAR2_1 = document.getElementById('sipkaAR2_1');
const sipkaAR2_2 = document.getElementById('sipkaAR2_2');
const sipkaOstatni = document.getElementById('sipkaOstatni');
const sipkaOstatni1 = document.getElementById('sipkaOstatni1');
const sipkaOstatni2 = document.getElementById('sipkaOstatni2');
const sipkaOstatni3 = document.getElementById('sipkaOstatni3');
const sipkaSituace = document.getElementById('sipkaSituace');

const pridatKladPF = document.getElementById('pridatKladPF');
const pridatZaporPF = document.getElementById('pridatZaporPF');
const vlastnostiPFSelect = document.getElementById('vlastnostiPFSelect');
const kladyZaporyPFDiv = document.getElementById('kladyZaporyPFDiv');

const pridatKladOT = document.getElementById('pridatKladOT');
const pridatZaporOT = document.getElementById('pridatZaporOT');
const vlastnostiOTSelect = document.getElementById('vlastnostiOTSelect');
const kladyZaporyOTDiv = document.getElementById('kladyZaporyOTDiv');
const kladyZaporyOTDivFinal = document.getElementById('kladyZaporyOTDivFinal');

const pridatKladFyzicka = document.getElementById('pridatKladFyzicka');
const pridatZaporFyzicka = document.getElementById('pridatZaporFyzicka');
const vlastnostiFyzickaSelect = document.getElementById('vlastnostiFyzickaSelect');
const kladyZaporyFyzickaDiv = document.getElementById('kladyZaporyFyzickaDiv');

const pridatKladSpoluprace = document.getElementById('pridatKladSpoluprace');
const pridatZaporSpoluprace = document.getElementById('pridatZaporSpoluprace');
const vlastnostiSpolupraceSelect = document.getElementById('vlastnostiSpolupraceSelect');
const kladyZaporySpolupraceDiv = document.getElementById('kladyZaporySpolupraceDiv');

const pridatKladPFAR1 = document.getElementById('pridatKladPFAR1');
const pridatZaporPFAR1 = document.getElementById('pridatZaporPFAR1');
const vlastnostiPFAR1Select = document.getElementById('vlastnostiPFAR1Select');
const kladyZaporyPFAR1Div = document.getElementById('kladyZaporyPFAR1Div');

const pridatKladPohybAR1 = document.getElementById('pridatKladPohybAR1');
const pridatZaporPohybAR1 = document.getElementById('pridatZaporPohybAR1');
const vlastnostiPohybAR1Select = document.getElementById('vlastnostiPohybAR1Select');
const kladyZaporyPohybAR1Div = document.getElementById('kladyZaporyPohybAR1Div');

const pridatKladPFAR2 = document.getElementById('pridatKladPFAR2');
const pridatZaporPFAR2 = document.getElementById('pridatZaporPFAR2');
const vlastnostiPFAR2Select = document.getElementById('vlastnostiPFAR2Select');
const kladyZaporyPFAR2Div = document.getElementById('kladyZaporyPFAR2Div');

const pridatKladPohybAR2 = document.getElementById('pridatKladPohybAR2');
const pridatZaporPohybAR2 = document.getElementById('pridatZaporPohybAR2');
const vlastnostiPohybAR2Select = document.getElementById('vlastnostiPohybAR2Select');
const kladyZaporyPohybAR2Div = document.getElementById('kladyZaporyPohybAR2Div');

const kladyZaporyPFInputHidden = document.getElementById('kladyZaporyPFInputHidden');
const kladyZaporyOTInputHidden = document.getElementById('kladyZaporyOTInputHidden');
const kladyZaporyOTInputHiddenFinal = document.getElementById('kladyZaporyOTInputHiddenFinal');
const kladyZaporyFyzickaInputHidden = document.getElementById('kladyZaporyFyzickaInputHidden');
const kladyZaporySpolupraceInputHidden = document.getElementById('kladyZaporySpolupraceInputHidden');
const kladyZaporyPFAR1InputHidden = document.getElementById('kladyZaporyPFAR1InputHidden');
const kladyZaporyPohybAR1InputHidden = document.getElementById('kladyZaporyPohybAR1InputHidden');
const kladyZaporyPFAR2InputHidden = document.getElementById('kladyZaporyPFAR2InputHidden');
const kladyZaporyPohybAR2InputHidden = document.getElementById('kladyZaporyPohybAR2InputHidden');

const zpravaStavInput = document.getElementById('zpravaStavInput');
const odeslatBtn = document.getElementById('odeslatBtn');
const vytvoritDocxBtn = document.getElementById('vytvoritDocxBtn');
const posudekVytvoritDocxBtn = document.getElementById('posudekVytvoritDocxBtn');
const ulozitBtn = document.getElementById('ulozitBtn');

if(posudekVytvoritDocxBtn != null) {
    posudekVytvoritDocxBtn.addEventListener('click', function () {
        window.location.href = '/generujDOCX';
    });
}


document.addEventListener('DOMContentLoaded', function () {
    if(document.getElementById('jeUlozeno') != null &&
        document.getElementById('jeUlozeno').textContent === "true"){
        showFlashAnimation();
    }

    vytvorDivyPodleDatvVInputu(vlastnostiPFSelect, "PF", kladyZaporyPFDiv, kladyZaporyPFInputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiOTSelect, "OT", kladyZaporyOTDiv, kladyZaporyOTInputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiFyzickaSelect, "Fyzicka", kladyZaporyFyzickaDiv,
        kladyZaporyFyzickaInputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiSpolupraceSelect, "Spoluprace", kladyZaporySpolupraceDiv,
         kladyZaporySpolupraceInputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiPFAR1Select, "PFARJedna", kladyZaporyPFAR1Div,
         kladyZaporyPFAR1InputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiPohybAR1Select, "PohybARJedna", kladyZaporyPohybAR1Div,
        kladyZaporyPohybAR1InputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiPFAR2Select, "PFARDva", kladyZaporyPFAR2Div,
        kladyZaporyPFAR2InputHidden);
    vytvorDivyPodleDatvVInputu(vlastnostiPohybAR2Select, "PFARDva", kladyZaporyPohybAR2Div,
        kladyZaporyPohybAR2InputHidden);
});

if(ulozitBtn != null){
    ulozitBtn.addEventListener('click', function () {
        zpravaStavInput.value = 0;
    });
}

if(odeslatBtn != null){
    odeslatBtn.addEventListener('click', function () {
        zpravaStavInput.value = 1;
    });
}

if(vytvoritDocxBtn != null){
    vytvoritDocxBtn.addEventListener('click', function () {
        zpravaStavInput.value = 2;
    });
}

pridatKladPF.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiPFSelect, kladyZaporyPFDiv, "PF", kladyZaporyPFInputHidden);
});

pridatZaporPF.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiPFSelect, kladyZaporyPFDiv, "PF", kladyZaporyPFInputHidden);
});

pridatKladOT.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiOTSelect, kladyZaporyOTDiv, "OT", kladyZaporyOTInputHidden);
});

pridatZaporOT.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiOTSelect, kladyZaporyOTDiv, "OT", kladyZaporyOTInputHidden);
});

pridatKladFyzicka.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiFyzickaSelect, kladyZaporyFyzickaDiv,
        "Fyzicka", kladyZaporyFyzickaInputHidden);
});

pridatZaporFyzicka.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiFyzickaSelect, kladyZaporyFyzickaDiv,
        "Fyzicka", kladyZaporyFyzickaInputHidden);
});

pridatKladSpoluprace.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiSpolupraceSelect, kladyZaporySpolupraceDiv,
        "Spoluprace", kladyZaporySpolupraceInputHidden);
});

pridatZaporSpoluprace.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiSpolupraceSelect, kladyZaporySpolupraceDiv,
        "Spoluprace", kladyZaporySpolupraceInputHidden);
});

pridatKladPFAR1.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiPFAR1Select, kladyZaporyPFAR1Div,
        "PFARJedna", kladyZaporyPFAR1InputHidden);
});

pridatZaporPFAR1.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiPFAR1Select, kladyZaporyPFAR1Div,
        "PFARJedna", kladyZaporyPFAR1InputHidden);
});

pridatKladPohybAR1.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiPohybAR1Select, kladyZaporyPohybAR1Div,
        "PohybARJedna", kladyZaporyPohybAR1InputHidden);
});

pridatZaporPohybAR1.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiPohybAR1Select, kladyZaporyPohybAR1Div,
        "PohybARJedna", kladyZaporyPohybAR1InputHidden);
});

pridatKladPFAR2.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiPFAR2Select, kladyZaporyPFAR2Div,
        "PFARDva", kladyZaporyPFAR2InputHidden);
});

pridatZaporPFAR2.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiPFAR2Select, kladyZaporyPFAR2Div,
        "PFARDva", kladyZaporyPFAR2InputHidden);
});

pridatKladPohybAR2.addEventListener('click', function () {
    pridejVlastnost(true, vlastnostiPohybAR2Select, kladyZaporyPohybAR2Div,
        "PohybARDva", kladyZaporyPohybAR2InputHidden);
});

pridatZaporPohybAR2.addEventListener('click', function () {
    pridejVlastnost(false, vlastnostiPohybAR2Select, kladyZaporyPohybAR2Div,
        "PohybARDva", kladyZaporyPohybAR2InputHidden);
});

function vypisDoInputuVlastnosti(select, jeKlad, existingDivs, kladyZaporyInputHidden) {
    var idVlastnost = select[select.selectedIndex].value;
    var index = kladyZaporyInputHidden.value.indexOf(',' + idVlastnost + '+');
    var index2 = kladyZaporyInputHidden.value.indexOf(',' + idVlastnost + '-');

    if (index >= 0 || index2 >= 0) {
        if (kladyZaporyInputHidden.value.indexOf(',' + idVlastnost + '+') !== -1 && !jeKlad) {
            kladyZaporyInputHidden.value =
                kladyZaporyInputHidden.value.replace(',' + idVlastnost + '+',
                    ',' + idVlastnost + '-');
        } else if (kladyZaporyInputHidden.value.indexOf(',' + idVlastnost + '-') !== -1 && jeKlad) {
            kladyZaporyInputHidden.value =
                kladyZaporyInputHidden.value.replace(',' + idVlastnost + '-',
                    ',' + idVlastnost + '+');
        }
        return;
    }
    if (existingDivs.length >= 5) {
        return;
    }
    if (jeKlad) {
        kladyZaporyInputHidden.value += ',' + idVlastnost + '+,';
    } else {
        kladyZaporyInputHidden.value += ',' + idVlastnost + '-,';
    }
}

function odstranZInputuVlastnost(idVlastnost, jeKlad, kladyZaporyInputHidden) {
    if (jeKlad) {
        kladyZaporyInputHidden.value =
            kladyZaporyInputHidden.value.replace(',' + idVlastnost + '+', '');
    } else {
        kladyZaporyInputHidden.value =
            kladyZaporyInputHidden.value.replace(',' + idVlastnost + '-', '');
    }

}

function pridejVlastnost(jeKlad, select, kladyZaporyDiv, typ, kladyZaporyInputHidden) {
    var existingDivs =
        document.querySelectorAll('#' + kladyZaporyDiv.id + ' > div');

    var selectedOption = select[select.selectedIndex].text;
    // Vytvoření nového divu
    var div = document.createElement('div');
    div.id = typ + select[select.selectedIndex].value;
    div.value = select.value;
    var myDiv = document.getElementById(div.id);
    var pridat = true;
    if (myDiv != null) {
        div = myDiv;
        pridat = false;
    }
    if (jeKlad) {
        div.textContent = '+ ' + selectedOption;
        div.style.background = "lightgreen";
        div.style.color = "black";
        div.style.border = "2px solid black"
    } else {
        div.textContent = '- ' + selectedOption;
        div.style.background = "red";
        div.style.color = "white";
        div.style.border = "2px solid white"
    }
    div.style.padding = "5px";
    div.style.paddingLeft = "10px";
    div.style.paddingRight = "5px";
    div.style.margin = "10px";
    div.style.borderRadius = "25px";
    div.style.justifyContent = "space-between";
    div.style.display = "flex";
    div.style.textAlign = "left";
    // Přidání divu pod element Select
    vypisDoInputuVlastnosti(select, jeKlad, existingDivs, kladyZaporyInputHidden);
    if (existingDivs.length >= 5) {
        return; // Pokud je počet divů větší nebo roven 5, ukončit funkci
    }
    // Vytvoření obrázku
    const img = document.createElement('img');
    img.src = '/css/img/remove_icon.png';
    img.style.width = "20px";
    img.style.height = "20px";
    img.style.marginTop = "auto";
    img.style.marginBottom = "auto";
    img.style.verticalAlign = 'middle';
    img.addEventListener('mouseenter', function (event) {
        img.src = '/css/img/remove_icon_active.png';
    });
    img.addEventListener('mouseleave', function (event) {
        img.src = '/css/img/remove_icon.png';
    });

    img.addEventListener('click', function (event) {
        const idVlastnost = div.id.replace(/\D/g, '');
        kladyZaporyDiv.removeChild(div);
        odstranZInputuVlastnost(idVlastnost, jeKlad, kladyZaporyInputHidden);
    });

    div.appendChild(img);
    if (pridat) {
        kladyZaporyDiv.appendChild(div);
    }

}

function vytvorDivyPodleDatvVInputu(select, typ, kladyZaporyDiv, kladyZaporyInputHidden) {
    // Rozdělíme vstupní řetězec podle čárky
    const inputVal = kladyZaporyInputHidden.value;
    let pridaneDivySoucet = 0;
    let items = inputVal.split(',');
    for (let i = 0; i < items.length; i++) {
        let jeKlad = false;
        var idVlastnost = '';
        if (items[i].indexOf('+') !== -1) {
            idVlastnost = items[i].replace('+', '');
            jeKlad = true;
        } else if (items[i].indexOf('-') !== -1) {
            idVlastnost = items[i].replace('-', '');
            jeKlad = false;
        }
        if (idVlastnost.length === 0 || isNaN(idVlastnost)) {
            continue;
        }
        let div = document.createElement('div');
        div.id = typ + idVlastnost;
        div.value = idVlastnost;
        let textContent = "";
        // Projdeme všechny <option> elementy v <select>
        for (let j = 0; j < select.options.length; j++) {
            var option = select.options[j];
            if (option.value === idVlastnost) {
                textContent = option.text;
                break;
            }
        }
        if (jeKlad) {
            div.textContent = '+ ' + textContent;
            div.style.background = "lightgreen";
            div.style.color = "black";
            div.style.border = "2px solid black"
        } else {
            div.textContent = '- ' + textContent;
            div.style.background = "red";
            div.style.color = "white";
            div.style.border = "2px solid white"
        }
        div.style.padding = "5px";
        div.style.paddingLeft = "10px";
        div.style.paddingRight = "5px";
        div.style.margin = "10px";
        div.style.borderRadius = "25px";
        div.style.justifyContent = "space-between";
        div.style.display = "flex";
        div.style.textAlign = "left";
        // Vytvoření obrázku
        const img = document.createElement('img');
        img.src = '/css/img/remove_icon.png';
        img.style.width = "20px";
        img.style.height = "20px";
        img.style.marginTop = "auto";
        img.style.marginBottom = "auto";
        img.style.verticalAlign = 'middle';
        img.addEventListener('mouseenter', function (event) {
            img.src = '/css/img/remove_icon_active.png';
        });
        img.addEventListener('mouseleave', function (event) {
            img.src = '/css/img/remove_icon.png';
        });

        img.addEventListener('click', function (event) {

            const idVlastnost = div.id.replace(/\D/g, '');
            kladyZaporyDiv.removeChild(div);
            odstranZInputuVlastnost(idVlastnost, jeKlad, kladyZaporyInputHidden);
        });


        pridaneDivySoucet++;
        if(document.title === 'SKFS – nový posudek'){
            div.appendChild(img);
        }
        kladyZaporyDiv.appendChild(div);
        if (pridaneDivySoucet >= 5) {
            return;
        }
    }
}

function showOrHide(div, sipkaImg, headerOffset) {

    if (div.style.display === "none") {
        div.style.display = "block";
        sipkaImg.style.transform = 'rotate(0deg)';
        var elementPosition = div.getBoundingClientRect().top;
        var offsetPosition = elementPosition + window.pageYOffset - headerOffset;

        window.scrollTo({
            top: offsetPosition,
            behavior: "smooth"
        });
    } else {
        div.style.display = "none";
        sipkaImg.style.transform = 'rotate(180deg)';
    }

}

const offsetR = 105;
const offsetAR1 = 125;
const offsetAR2 = 145;
const offsetSituace = 165;
const offsetOstatni = 185;
// R
listaR.addEventListener('click', function () {
    showOrHide(blokR, sipkaR, offsetR);
});
listaR1.addEventListener('click', function () {
    showOrHide(blokR1, sipkaR1, offsetR);
});
listaR2.addEventListener('click', function () {
    showOrHide(blokR2, sipkaR2, offsetR);
});
listaR3.addEventListener('click', function () {
    showOrHide(blokR3, sipkaR3, offsetR);
});
listaR4.addEventListener('click', function () {
    showOrHide(blokR4, sipkaR4, offsetR);
});
// AR1
listaAR1.addEventListener('click', function () {
    showOrHide(blokAR1, sipkaAR1, offsetAR1);
});
listaAR1_1.addEventListener('click', function () {
    showOrHide(blokAR1_1, sipkaAR1_1, offsetAR1);
});
listaAR1_2.addEventListener('click', function () {
    showOrHide(blokAR1_2, sipkaAR1_2, offsetAR1);
});
// AR2
listaAR2.addEventListener('click', function () {
    showOrHide(blokAR2, sipkaAR2, offsetAR2);
});
listaAR2_1.addEventListener('click', function () {
    showOrHide(blokAR2_1, sipkaAR2_1, offsetAR2);
});
listaAR2_2.addEventListener('click', function () {
    showOrHide(blokAR2_2, sipkaAR2_2, offsetAR2);
});
// Situace
listaSituace.addEventListener('click', function () {
    showOrHide(blokSituace, sipkaSituace, offsetSituace);
});
// Ostatni
listaOstatni.addEventListener('click', function () {
    showOrHide(blokOstatni, sipkaOstatni, offsetOstatni);
});
listaOstatni1.addEventListener('click', function () {
    showOrHide(blokOstatni1, sipkaOstatni1, offsetOstatni);
});
listaOstatni2.addEventListener('click', function () {
    showOrHide(blokOstatni2, sipkaOstatni2, offsetOstatni);
});
listaOstatni3.addEventListener('click', function () {
    showOrHide(blokOstatni3, sipkaOstatni3, offsetOstatni);
});

// polocas
polocas1?.addEventListener('input', function () {
    checkValid(polocas1);
});
polocas2?.addEventListener('input', function () {
    checkValid(polocas2);
});

function checkValidPolocasAndVysledekSkore() {
    if(polocas.checkValidity()){
        var inputValue1 = polocas.value;
        var inputValue2 = vysledek.value;

        // Vytvoří regulární výraz pro extrakci čísel
        var regex = /\d+/g;

        // Extrahuje čísla z hodnot
        var numbers1 = inputValue1.match(regex);
        var numbers2 = inputValue2.match(regex);
        if(numbers1[0] > numbers2[0] || numbers1[1] > numbers2[1]) {
            polocas.classList.add('invalid-input');
            polocas.setCustomValidity("Poločas je větší než výsledek");
        }
    }
}
polocas.addEventListener('input', function () {
    polocas.classList.remove('invalid-input');
    polocas.setCustomValidity("");
    checkValid(polocas);
    checkValidPolocasAndVysledekSkore();

});
vysledek?.addEventListener('input', function () {
    polocas.classList.remove('invalid-input');
    polocas.setCustomValidity("");
    checkValid(polocas);
    checkValid(vysledek);
    checkValidPolocasAndVysledekSkore();
});
znamkaRHCH.addEventListener('input', function () {
    checkValid(znamkaRHCH);
    setZnamkaCeleCislo(znamkaRHCH);
});
znamkaAR1HCH.addEventListener('input', function () {
    checkValid(znamkaAR1HCH);
    setZnamkaCeleCislo(znamkaAR1HCH);
});
znamkaAR2HCH.addEventListener('input', function () {
    checkValid(znamkaAR2HCH);
    setZnamkaCeleCislo(znamkaAR2HCH);
});

var inputElementR = document.getElementById('jmenoR');
var inputElementAR1 = document.getElementById('jmenoAR1');
var inputElementAR2 = document.getElementById('jmenoAR2');
var inputElementTD = document.getElementById('jmenoTD');
var idFacrLabelR = document.getElementById('idFacrR');
var idFacrLabelAR1 = document.getElementById('idFacrAR1');
var idFacrLabelAR2 = document.getElementById('idFacrAR2');
var idFacrLabelTD = document.getElementById('idFacrTD');

var idFacrRHiddenInput = document.getElementById('idFacrRHiddenInput');
var idFacrAR1HiddenInput = document.getElementById('idFacrAR1HiddenInput');
var idFacrAR2HiddenInput = document.getElementById('idFacrAR2HiddenInput');
var idFacrTDHiddenInput = document.getElementById('idFacrTDHiddenInput');

inputElementR.addEventListener('input', function () {
    var selectedOption = findOptionByValue(this.value, 'rozhodciList');
    if (selectedOption) {
        idFacrLabelR.textContent = selectedOption.value;
        idFacrRHiddenInput.value = selectedOption.value;
        inputElementR.value = selectedOption.label;
        listaRJmeno.textContent = selectedOption.label + " (" + selectedOption.value + ")";
    } else {
        idFacrRHiddenInput.value = "";
        idFacrLabelR.textContent = "";
        listaRJmeno.textContent = "";
    }
    checkDuplicity();
});

inputElementAR1.addEventListener('input', function () {
    var selectedOption = findOptionByValue(this.value, 'ar1List');
    if (selectedOption) {
        idFacrLabelAR1.textContent = selectedOption.value;
        idFacrAR1HiddenInput.value = selectedOption.value;
        inputElementAR1.value = selectedOption.label;
        listaAR1Jmeno.textContent = selectedOption.label + " (" + selectedOption.value + ")";
    } else {
        idFacrAR1HiddenInput.value = "";
        idFacrLabelAR1.textContent = "";
        listaAR1Jmeno.textContent = "";
    }
    checkDuplicity();
});

inputElementAR2.addEventListener('input', function () {
    var selectedOption = findOptionByValue(this.value, 'ar2List');
    if (selectedOption) {
        idFacrAR2HiddenInput.value = selectedOption.value;
        idFacrLabelAR2.textContent = selectedOption.value;
        inputElementAR2.value = selectedOption.label;
        listaAR2Jmeno.textContent = selectedOption.label + " (" + selectedOption.value + ")";
    } else {
        idFacrAR2HiddenInput.value = "";
        idFacrLabelAR2.textContent = "";
        listaAR2Jmeno.textContent = "";
    }
    checkDuplicity();
});

inputElementTD.addEventListener('input', function () {
    var selectedOption = findOptionByValue(this.value, 'tdList');
    if (selectedOption) {
        idFacrTDHiddenInput.value = selectedOption.value;
        idFacrLabelTD.textContent = selectedOption.value;
        inputElementTD.value = selectedOption.label;
    } else {
        idFacrTDHiddenInput.value = "";
        idFacrLabelTD.textContent = "";
    }
});

function checkValid(inputElement) {
    const isValid = inputElement.checkValidity();
    if (isValid) {
        inputElement.classList.remove('invalid-input');
    } else {
        inputElement.classList.add('invalid-input');
    }
}

function checkDuplicity() {
    inputElementR.classList.remove('invalid-input');
    inputElementAR1.classList.remove('invalid-input');
    inputElementAR2.classList.remove('invalid-input');

    if (idFacrLabelR.textContent.length > 0 &&
        idFacrLabelAR1.textContent.length > 0 && idFacrLabelAR2.textContent.length > 0
        && idFacrLabelR.textContent === idFacrLabelAR1.textContent
        && idFacrLabelR.textContent === idFacrLabelAR2.textContent &&
        idFacrLabelAR1.textContent === idFacrLabelAR2.textContent) {
        inputElementR.classList.add('invalid-input');
        inputElementAR1.classList.add('invalid-input');
        inputElementAR2.classList.add('invalid-input');
        inputElementAR1.setCustomValidity("Duplicitní rozhodčí");
        inputElementAR2.setCustomValidity("Duplicitní rozhodčí");
        return;
    }
    if (idFacrLabelR.textContent.length > 0 &&
        idFacrLabelAR1.textContent.length > 0
        && idFacrLabelR.textContent === idFacrLabelAR1.textContent) {
        inputElementR.classList.add('invalid-input');
        inputElementAR1.classList.add('invalid-input');
        inputElementAR1.setCustomValidity("Duplicitní rozhodčí");
        return;
    }
    if (idFacrLabelR.textContent.length > 0 &&
        idFacrLabelAR2.textContent.length > 0
        && idFacrLabelR.textContent === idFacrLabelAR2.textContent) {
        inputElementR.classList.add('invalid-input');
        inputElementAR2.classList.add('invalid-input');
        inputElementAR2.setCustomValidity("Duplicitní rozhodčí");
        return;
    }
    if (idFacrLabelAR2.textContent.length > 0 &&
        idFacrLabelAR1.textContent.length > 0
        && idFacrLabelAR2.textContent === idFacrLabelAR1.textContent) {
        inputElementAR1.classList.add('invalid-input');
        inputElementAR2.classList.add('invalid-input');
        inputElementAR2.setCustomValidity("Duplicitní rozhodčí");
    }
    inputElementR.setCustomValidity('');
    inputElementAR1.setCustomValidity('');
    inputElementAR2.setCustomValidity('');
}

function findOptionByValue(value, listType) {
    var datalistOptions = document.getElementById(listType).getElementsByTagName('option');
    for (var i = 0; i < datalistOptions.length; i++) {
        if (datalistOptions[i].value === value) {
            return datalistOptions[i];
        }
    }
    return null;
}

const znamkaRHCHRadek = document.getElementById('znamkaRHCHRadek');
const znamkaR = document.getElementById('znamkaR');
const znamkaAR1HCHRadek = document.getElementById('znamkaAR1HCHRadek');
const znamkaAR1 = document.getElementById('znamkaAR1');
const znamkaAR2HCHRadek = document.getElementById('znamkaAR2HCHRadek');
const znamkaAR2 = document.getElementById('znamkaAR2');

znamkaR?.addEventListener('input', function () {
    checkValidZnamka(znamkaR, znamkaRHCHRadek);
});
znamkaAR1?.addEventListener('input', function () {
    checkValidZnamka(znamkaAR1, znamkaAR1HCHRadek);
});
znamkaAR2?.addEventListener('input', function () {
    checkValidZnamka(znamkaAR2, znamkaAR2HCHRadek);
});

function setZnamkaCeleCislo(inputElement) {
    const value = parseFloat(inputElement.value);
    if (value === 6 || value === 7 || value === 8 || value === 9) {
        inputElement.value = value + ".0";
    }
}

function checkValidZnamka(inputElement, radek) {
    const isValid = inputElement.checkValidity();
    if (isValid) {
        inputElement.classList.remove('invalid-input');
        const value = parseFloat(inputElement.value);
        if (value === 7.8 || value === 7.9) {
            radek.style.display = 'table-row';
        } else {
            setZnamkaCeleCislo(inputElement);
            radek.style.display = 'none';
        }
    } else {
        inputElement.classList.add('invalid-input');
    }
}

function topFunction() {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}

function showFlashAnimation() {
    var flashElement = document.getElementById('flashAnimation');

    // Zobrazí flash animaci
    flashElement.style.display = 'block';

    // Spustí animaci
    flashElement.classList.add('flash-animation');

    // Skryje flash animaci po skončení animace
    setTimeout(function() {
        flashElement.style.display = 'none';
        flashElement.classList.remove('flash-animation');
    }, 3000); // čas trvání animace v milisekundách (1s v tomto případě)
}


window.onscroll = function () {
    // Získání elementu, který je přilepený
    var stickyElement = document.querySelector('.sticky-element');
    // Získání výšky přilepeného elementu
    var stickyElementHeight = stickyElement.offsetHeight;

    // Získání vzdálenosti od vrchu stránky, při které se má element "přilepit"
    var stickyPosition = stickyElement.offsetTop;

    // Získání aktuální pozice při posouvání
    var scrollPosition = window.scrollY || document.documentElement.scrollTop;

    // Podmínka pro přilepení nebo odlepení elementu
    if (scrollPosition > stickyPosition) {
        stickyElement.classList.add('sticky');
    } else {
        stickyElement.classList.remove('sticky');
    }
};

function prejdiNaProfilClena(id) {
    window.location.href = '/profil?id=' + document.getElementById(id).textContent;
}


