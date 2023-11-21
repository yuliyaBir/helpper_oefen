"use strict";
import {byId, toon, verberg, setText, verwijderChildElementenVan} from "./util.js";
const LOGGED_COOKIE = "quarkus-credential";
const inputsRuimte = byId("inputsRuimte");

let user;
verbergAllesBehalveForm();
findHuidigeUser();

async function findHuidigeUser() {
    const response = await fetch("/api/users/me");
    if (response.ok) {
        user = await response.json();
        const rol = user.rol;
        switch (rol) {
            case "budgethouder":
                toon("securedContentVoorBudgethouder");
                break;
            case "assistent":
                toon("securedContentVoorAssistent");
                break;
            default:
                console.log(`Sorry, je heb geen rol aangeduid`);
        }
        toonAllesBehalveFouten();
        // setText("rol", user.rol);
        // setText("voornaam", user.voornaam);
        // setText("familienaam", user.familienaam);
        // await schermBepalenDoorRol(user);
    } else {
        if (response.status === 404) {
            toon("nietGevonden");
        } else {
            toon("storing");
        }
    }
}
async function schermBepalenDoorRol(user){
    const  navigatieBar = byId("navigatieBar");
    switch (user.rol) {
        case "budgethouder" : {
            // createUlElement();
        verwijderChildElementenVan(navigatieBar);
        const barUl = document.createElement("ul");
        barUl.setAttribute("id", "barUl");
        navigatieBar.appendChild(barUl);
            console.log(user.id);
            createLiElementLi("nieuwePrestatieLi", "barUl", "Nieuwe prestaties om goed te keuren", `prestaties/budgethouder/${user.id}/zonderGoedkeuring`);
            createLiElementLi("goedgekeurdePrestatieLi", "barUl", "Goedgekeurde prestaties", `prestaties/budgethouder/${user.id}/metGoedkeuring`);
        toon("navigatieBar");} break;
        case "assistent" : {
            verwijderChildElementenVan(navigatieBar);
            const barUl = document.createElement("ul");
            barUl.setAttribute("id", "barUl");
            navigatieBar.appendChild(barUl);
            console.log(user.id);
            createLiElementLi("toevoegen", "barUl", "Voeg een nieuwe prestatie toe.", "prestaties/nieuw")
            createLiElementLi("zonderGoedkeuring", "barUl", "Aangemaakte prestaties zonder goedkeuring", `prestaties/assistent/${user.id}/zonderGoedkeuring`);
            createLiElementLi("metGoedkeuring", "barUl", "Prestaties met goedkeuring", `prestaties/assistent/${user.id}/metGoedkeuring`);
            toon("navigatieBar");} break;
        default : toon("verkeerdeRol");
    }
}
function toonAllesBehalveFouten(){
    toon("navigatieBar");
    // toon("jouwPrestatie");
    // toon("hello");
    // toon("form");
    // verberg("omschrijving");
    verberg("storing");
    verberg("verkeerdeRol");
    verberg("tabelRuimte");
}
function verbergAllesBehalveForm(){
    verberg("navigatieBar");
    verberg("nietGevonden");
    verberg("storing");
    verberg("verkeerdeRol");
    // verberg("jouwPrestatie");
    verberg("tabelRuimte");
    // toon("form");
}
function createLiElementLi(id, ul, text, methodLink) {
    verberg("tabelRuimte");
    const li = document.createElement("li");
    li.setAttribute("id", id);
    byId(ul).appendChild(li);
    const link = document.createElement("a");
    link.setAttribute("href", "#");
    link.innerText = text;
    li.appendChild(link);
    if (methodLink !== "prestaties/nieuw"){
        link.onclick = function () {
            createTabelMetPrestaties(methodLink).then(r => console.log("Create tabel"));
        }
    } else {
        link.onclick = async function (){
           await createEnToonNieuwePrestatie();
        }
    }

}
async function createTabelMetPrestaties (methodLink){
    const response = await fetch(`${methodLink}`);
    if (response.ok){
        const prestaties = await response.json();
        console.log(prestaties);
        const tabelRuimte = byId("tabelRuimte");
        verwijderChildElementenVan(tabelRuimte);
        toon("tabelRuimte");
        const tabel = document.createElement("table");
        tabelRuimte.appendChild(tabel);
        createTHead(tabel);
        for (const prestatie of prestaties){
                const tr = tabel.insertRow();
                tr.insertCell().innerText = prestatie.id;
                tr.insertCell().innerText = prestatie.naam;
                tr.insertCell().innerText = prestatie.omschrijving;
                tr.insertCell().innerText = `${prestatie.voornaam} ${prestatie.familienaam}`
            }
    } else{
        toon("storing");
    }
}
function createTHead(tabel){
    const tr1 = document.createElement("tr");
    tabel.appendChild(tr1);
    const thId = document.createElement("th");
    thId.innerText = "Id";
    tr1.appendChild(thId);
    const thNaam = document.createElement("th");
    thNaam.innerText = "Naam";
    tr1.appendChild(thNaam);
    const thOmschrijving = document.createElement("th");
    thOmschrijving.innerText = "Omschrijving";
    tr1.appendChild(thOmschrijving);
    const thAssisttentNaam = document.createElement("th");
    thAssisttentNaam.innerText = "Assistent";
    tr1.appendChild(thAssisttentNaam);
}
function createUlElement(){
    const navigatieBar = byId("navigatieBar");
    navigatieBar.setAttribute("id","navigatieBar");
    verwijderChildElementenVan(navigatieBar);
    const barUl = document.createElement("ul");
    barUl.setAttribute("id", "barUl");
    navigatieBar.appendChild(barUl);
}
byId("logout").onclick = function () {
    document.cookie = LOGGED_COOKIE + '=; Max-Age=0'
    window.location.href = "/";

}
async function createEnToonNieuwePrestatie(){
    var response = await fetch("/prestaties/nieuw",
        {method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(prestatie)
        });
    if (response.ok){
        console.log("toegevoegd!");
    } else{
        if (response.status === 409){
            const responseBody = await response.json();
            setText("conflict", responseBody.message);
            toon("conflict");
        } else {
            toon("storing");
        }
    }
}
// function createInputs(){
//     const labelVoorInput
// }