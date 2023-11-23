"use strict"
import {byId, toon, verberg, setText, verwijderChildElementenVan} from "./util.js";

const zonderGoedkeuring = byId("zonderGoedkeuring");
const metGoedkeuring = byId("metGoedkeuring");
const toevoegen = byId("toevoegen");
const prestatieBody = byId("prestatieBody");
const tabelRuimte = byId("tabelRuimte");
const prestatieHead = byId("prestatieHead");
findHuidigeUser();
async function findHuidigeUser() {
    const response = await fetch("/api/users/me");
    if (response.ok) {
        const user = await response.json();
        console.log(user)
        setText("rol", user.rol);
        setText("voornaam", user.voornaam);
        setText("familienaam", user.familienaam);
        zonderGoedkeuring.onclick = async function (){
            prestatiesZonderGoedkeuring(user);

        }
        metGoedkeuring.onclick = async function (){
            prestatiesMetGoedkeuring(user);
            toon("tabelRuimte");
        }

    }
}
async function prestatiesZonderGoedkeuring(user){
    const response = await fetch(`prestaties/assistent/${user.id}/zonderGoedkeuring`);
    if (response.ok){
        const prestaties = await response.json();
        if (prestaties.length !== 0){
            console.log(prestaties);
            toon("tabelRuimte");
            verwijderChildElementenVan(prestatieHead);
            const th = document.createElement("tr");
            prestatieHead.appendChild(th);
            th.insertCell().innerText =  "Id";
            th.insertCell().innerText =  "Naam";
            th.insertCell().innerText =  "Omschrijving";
            th.insertCell().innerText =  "Budgethouder";
            verwijderChildElementenVan(prestatieBody);
            for (const prestatie of prestaties){
                const tr = prestatieBody.insertRow();
                tr.insertCell().innerText =  prestatie.id;
                tr.insertCell().innerText =  prestatie.naam;
                tr.insertCell().innerText =  prestatie.omschrijving;
                tr.insertCell().innerText =  `${prestatie.voornaam} ${prestatie.familienaam}`;
            }
        } else{
            toon("geenPrestaties");
        }
    }
}
async function prestatiesMetGoedkeuring(user){
    const response = await fetch(`prestaties/assistent/${user.id}/metGoedkeuring`);
    if (response.ok){
        const prestaties = await response.json();
        if (prestaties.length !== 0){
        console.log(prestaties);
        toon("tabelRuimte");
        verwijderChildElementenVan(prestatieHead);
        const th = document.createElement("tr");
        prestatieHead.appendChild(th);
        th.insertCell().innerText =  "Id";
        th.insertCell().innerText =  "Naam";
        th.insertCell().innerText =  "Omschrijving";
        th.insertCell().innerText =  "Budgethouder";
        th.insertCell().innerText =  "Goedgekeurd op";
        th.insertCell().innerText =  "Commentaar";
        th.insertCell().innerText =  "Uren";
        verwijderChildElementenVan(prestatieBody);
        for (const prestatie of prestaties){
            const tr = prestatieBody.insertRow();
            tr.insertCell().innerText =  prestatie.id;
            tr.insertCell().innerText =  prestatie.naam;
            tr.insertCell().innerText =  prestatie.omschrijving;
            tr.insertCell().innerText =  `${prestatie.voornaam} ${prestatie.familienaam}`;
            tr.insertCell().innerText =  `${prestatie.goedkeuringen.goedgekeurd}`;
            tr.insertCell().innerText =  `${prestatie.goedkeuringen.commentaar}`;
            tr.insertCell().innerText =  `${prestatie.goedkeuringen.uren}`;
        }
        }else{
            toon("geenPrestaties");
        }
    }
}