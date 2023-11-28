"use strict";
import {byId, toon, verberg, setText, verwijderChildElementenVan} from "./util.js";

const LOGGED_COOKIE = "quarkus-credential";
const inputsRuimte = byId("inputsRuimte");
byId("registratie").onclick = function () {
    window.location = "registratie.html";
}
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
    } else {
        if (response.status === 404) {
            toon("nietGevonden");
        } else {
            toon("storing");
        }
    }
}

function toonAllesBehalveFouten() {
    toon("navigatieBar");
    verberg("storing");
    verberg("verkeerdeRol");
    verberg("tabelRuimte");
}

function verbergAllesBehalveForm() {
    verberg("navigatieBar");
    verberg("nietGevonden");
    verberg("storing");
    verberg("verkeerdeRol");
    verberg("tabelRuimte");
}

byId("logout").onclick = function () {
    document.cookie = LOGGED_COOKIE + '=; Max-Age=0'
    window.location.href = "/";

}