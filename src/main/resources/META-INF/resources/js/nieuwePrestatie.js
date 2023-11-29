"use strict"
import {byId, verberg, toon, setText, verwijderChildElementenVan} from "./util.js";

findHuidigeGebruiker();
let assistentId;

async function findHuidigeGebruiker() {
    let response = await fetch("/api/users/me",
        {method: "GET"});
    if (response.ok) {
        const user = await response.json();
        assistentId = user.id;
        console.log(assistentId);
    }
    const knopToevoegen = byId("toevoegen");
    knopToevoegen.disabled = false;
    const toegevoegd = byId("toegevoegd");
    toegevoegd.disabled = true;
    const inputNaam = byId("naam");
    const inputOmschrijving = byId("omschrijving");
    const inputVoornaam = byId("budgethouderVoornaam");
    const inputFamilienaam = byId("budgethouderFamilienaam");
    verbergPrestatieEnFouten();
    byId("toevoegen").onclick = async function () {
        verbergPrestatieEnFouten();
        if (!inputNaam.checkValidity()) {
            toon("naamFout");
            inputNaam.value = "";
            inputNaam.focus();
            return;
        }
        if (!inputOmschrijving.checkValidity()) {
            toon("omschrijvingFout");
            inputOmschrijving.focus();
            inputOmschrijving.value = "";
            return;
        }
        if (!inputVoornaam.checkValidity()) {
            toon("budgethouderVoornaamFout");
            inputVoornaam.focus();
            inputVoornaam.value = "";
            return;
        }
        if (!inputFamilienaam.checkValidity()) {
            toon("budgethouderFamilienaamFout");
            inputFamilienaam.focus();
            inputFamilienaam.value = "";
            return;
        }
        const prestatie = {
            assistentId: assistentId,
            budgethouderFamilienaam: inputFamilienaam.value,
            budgethouderVoornaam: inputVoornaam.value,
            naam: inputNaam.value,
            omschrijving: inputOmschrijving.value
        }
        await voegToe(prestatie);
    }

    function verbergPrestatieEnFouten() {
        verberg("prestatieTable");
        verberg("naamFout");
        verberg("omschrijvingFout");
        verberg("budgethouderVoornaamFout");
        verberg("budgethouderFamilienaamFout");
        verberg("storing");
        verberg("badRequest");
    }

    async function voegToe(prestatie) {
        var response = await fetch("http://localhost:8080/prestaties/nieuw",
            {
                method: "POST",
                headers: {'Content-Type': "application/json"},
                body: JSON.stringify(prestatie)
            });
        console.log(response);
        if (response.ok) {
            const createdPrestatie = await response.json();
            knopToevoegen.disabled = true;
            toegevoegd.hidden = false;
            byId("prestatieTable").hidden = false;
            console.log(createdPrestatie);
            const prestatieBody = byId("prestatieBody");
            const tr = prestatieBody.insertRow();
            tr.insertCell().innerText = createdPrestatie.id;
            tr.insertCell().innerText = createdPrestatie.naam;
            tr.insertCell().innerText = createdPrestatie.omschrijving;
            tr.insertCell().innerText = `${createdPrestatie.budgethouder.voornaam} ${createdPrestatie.budgethouder.familienaam}`;
        } else {
            if (response.status === 400) {
                setText("badRequest", "Deze persoon is geen budgethouder");
                toon("badRequest");
                inputVoornaam.value = "";
                inputVoornaam.focus();
                inputFamilienaam.value = "";
            } else{
                if (response.status === 404) {
                    setText("badRequest", "Deze budgethouder is niet gevonden");
                    toon("badRequest");
                    inputVoornaam.value = "";
                    inputVoornaam.focus();
                    inputFamilienaam.value = "";
                } else {
                    verberg("badRequest");
                    toon("storing");
                }
            }
        }
    }
}