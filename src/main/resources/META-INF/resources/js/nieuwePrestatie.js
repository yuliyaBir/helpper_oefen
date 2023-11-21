"use strict"
import {byId, verberg, toon, setText, verwijderChildElementenVan} from "./util.js";
findHuidigeGebruiker();
let assistentId;
async function findHuidigeGebruiker(){
    let response = await fetch("/api/users/me",
        {method: "GET"});
    if (response.ok){
        const user = await response.json();
        assistentId = user.id;
        console.log(assistentId);
}
    const inputNaam = byId("naam");
    const inputOmschrijving = byId("omschrijving");
    const inputVoornaam = byId("budgethouderVoornaam");
    const inputFamilienaam = byId("budgethouderFamilienaam");
    verbergPrestatieEnFouten();
byId("toevoegen").onclick = async function (){
    if (!inputNaam.checkValidity()){
        toon("naamFout");
        inputNaam.focus();
    }
    if (!inputOmschrijving.checkValidity()){
        toon("omschrijvingFout");
        inputOmschrijving.focus();
    }
    if (!inputVoornaam.checkValidity()){
        toon("budgethouderVoornaamFout");
        inputVoornaam.focus();
    }
    if (!inputFamilienaam.checkValidity()){
        toon("budgethouderFamilienaamFout");
        inputFamilienaam.focus();
    }
    const prestatie = {
        assistentId: assistentId,
        budgethouderFamilienaam : inputFamilienaam.value,
        budgethouderVoornaam : inputVoornaam.value,
        naam: inputNaam.value,
        omschrijving: inputOmschrijving.value
    }
    await voegToe(prestatie);
}

function verbergPrestatieEnFouten (){
    verberg("prestatieTable");
    verberg("naamFout");
    verberg("omschrijvingFout");
    verberg("budgethouderVoornaamFout");
    verberg("budgethouderFamilienaamFout");
    verberg("storing");
    verberg("conflict");
}
async function voegToe(prestatie) {
    var response = await fetch("http://localhost:8080/prestaties/nieuw",
        {
            method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(prestatie)
        });
    if (response.ok) {
        const createdPrestatie = await response.json();
        byId("prestatieTable").hidden = false;
        console.log(createdPrestatie);
        const prestatieBody = byId("prestatieBody");
        const tr = prestatieBody.insertRow();
        tr.insertCell().innerText =  createdPrestatie.id;
        tr.insertCell().innerText =  createdPrestatie.naam;
        tr.insertCell().innerText =  createdPrestatie.omschrijving;
        tr.insertCell().innerText =  `${createdPrestatie.budgethouder.voornaam} ${createdPrestatie.budgethouder.familienaam}`;
    } else {
        if (response.status === 409) {
            const responseBody = await response.json();
            setText("conflict", responseBody.message);
            toon("conflict");
        } else {
            toon("storing");
        }
    }
}
}