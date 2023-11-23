"use strict"
import {byId, verberg, toon, setText, verwijderChildElementenVan} from "./util.js";
const knopToevoegen = byId("toevoegen");
knopToevoegen.disabled = false;
const prestatie = JSON.parse(sessionStorage.getItem("prestatie"));
const prestatieBody = byId("prestatieBody");
const tr = prestatieBody.insertRow();
tr.insertCell().innerText =  prestatie.id;
tr.insertCell().innerText =  prestatie.naam;
tr.insertCell().innerText =  prestatie.omschrijving;
tr.insertCell().innerText = `${prestatie.voornaam} ${prestatie.familienaam}`
toon("prestatieTable");
const inputCommentaar = byId("commentaar");
const inputUren = byId("uren");
verbergFouten();
byId("toevoegen").onclick = async function (){
    verbergFouten();
    if (!inputCommentaar.checkValidity()){
        toon("commentaarFout");
        inputCommentaar.focus();
        return;
    }
    if (inputUren.value < 1){
        toon("urenFout");
        inputUren.focus();
        return;
    }
    const goedkeuring = {
        commentaar : inputCommentaar.value,
        uren : inputUren.value
    }
    await voegToe(goedkeuring);
}

function verbergFouten (){
    verberg("commentaarFout");
    verberg("urenFout");
    verberg("toegevoegd");
    verberg("storing");
    verberg("conflict");
}
async function voegToe(goedkeuring) {
    var response = await fetch(`/goedkeuringen/nieuwVoorPrestatie/${prestatie.id}`,
        {
            method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(goedkeuring)
        });
    if (response.ok) {
        knopToevoegen.disabled = true;
        toon("toegevoegd");
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