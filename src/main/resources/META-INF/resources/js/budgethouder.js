"use strict"
import {byId, toon, verberg, setText, verwijderChildElementenVan} from "./util.js";


const zonderGoedkeuring = byId("nieuwePrestaties");
const metGoedkeuring = byId("goedgekeurdePrestaties");
const prestatieBody = byId("prestatieBody");
const tabelRuimte = byId("tabelRuimte");
const prestatieHead = byId("prestatieHead");
const tabelNaam = byId("tabelNaam");
findHuidigeUser();

async function findHuidigeUser() {
    const response = await fetch("/api/users/me");
    if (response.ok) {
        const user = await response.json();
        console.log(user)
        setText("rol", user.rol);
        setText("voornaam", user.voornaam);
        setText("familienaam", user.familienaam);
        zonderGoedkeuring.onclick = async function () {
            await nieuwePrestaties(user);
        }
        metGoedkeuring.onclick = async function () {
            await prestatiesMetGoedkeuring(user);
        }
    }
}

async function nieuwePrestaties(user) {
    const response = await fetch(`prestaties/budgethouder/${user.id}/withoutGoedkeuring`);
    if (response.ok) {
        const prestaties = await response.json();
        if (prestaties.length !== 0) {
            console.log(prestaties);
            toon("tabelRuimte");
            verberg("geenPrestaties");
            tabelNaam.innerText = "Nieuwe prestaties zonder goedkeuring";
            verwijderChildElementenVan(prestatieHead);
            const th = document.createElement("tr");
            prestatieHead.appendChild(th);
            th.insertCell().innerText = "Id";
            th.insertCell().innerText = "Naam";
            th.insertCell().innerText = "Omschrijving";
            th.insertCell().innerText = "Assistent";
            th.insertCell().innerText = "Goedkeuren";
            verwijderChildElementenVan(prestatieBody);
            for (const prestatie of prestaties) {
                const tr = prestatieBody.insertRow();
                tr.insertCell().innerText = prestatie.id;
                tr.insertCell().innerText = prestatie.naam;
                tr.insertCell().innerText = prestatie.omschrijving;
                tr.insertCell().innerText = `${prestatie.voornaam} ${prestatie.familienaam}`;
                const td = tr.insertCell();
                const button = document.createElement("button");
                td.appendChild(button);
                button.innerText = "goedkeuren";
                button.onclick = async function () {
                    sessionStorage.setItem("prestatie", JSON.stringify(prestatie));
                    window.location = "nieuweGoedkeuring.html";
                }
            }
        } else {
            toon("geenPrestaties");
            verberg("tabelRuimte");
        }
    }
}

async function prestatiesMetGoedkeuring(user) {
    const response = await fetch(`prestaties/budgethouder/${user.id}/withGoedkeuring`);
    if (response.ok) {
        const prestaties = await response.json();
        if (prestaties.length !== 0) {
            console.log(prestaties);
            toon("tabelRuimte");
            verberg("geenPrestaties");
            tabelNaam.innerText = "Goedgekeurde prestaties";
            verwijderChildElementenVan(prestatieHead);
            const th = document.createElement("tr");
            prestatieHead.appendChild(th);
            th.insertCell().innerText = "Id";
            th.insertCell().innerText = "Naam";
            th.insertCell().innerText = "Omschrijving";
            th.insertCell().innerText = "Assistent";
            th.insertCell().innerText = "Goedgekeurd op";
            th.insertCell().innerText = "Commentaar";
            th.insertCell().innerText = "Uren";
            verwijderChildElementenVan(prestatieBody);
            for (const prestatie of prestaties) {
                const tr = prestatieBody.insertRow();
                tr.insertCell().innerText = prestatie.id;
                tr.insertCell().innerText = prestatie.naam;
                tr.insertCell().innerText = prestatie.omschrijving;
                tr.insertCell().innerText = `${prestatie.voornaam} ${prestatie.familienaam}`;
                tr.insertCell().innerText = `${prestatie.goedkeuring.goedgekeurd}`;
                tr.insertCell().innerText = `${prestatie.goedkeuring.commentaar}`;
                tr.insertCell().innerText = `${prestatie.goedkeuring.uren}`;
            }
        } else {
            toon("geenPrestaties");
            verberg("tabelRuimte");
        }
    }
}
