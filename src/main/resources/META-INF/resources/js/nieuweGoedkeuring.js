"use strict"
import {byId, verberg, toon, setText, verwijderChildElementenVan} from "./util.js";

toon("prestatieTable");
const prestatie = JSON.parse(sessionStorage.getItem("prestatie"));
const prestatieBody = byId("prestatieBody");
const tr = prestatieBody.insertRow();
tr.insertCell().innerText =  prestatie.id;
tr.insertCell().innerText =  prestatie.naam;
tr.insertCell().innerText =  prestatie.omschrijving;
tr.insertCell().innerText =  `${prestatie.voornaam} ${prestatie.familienaam}`
    const inputCommentaar = byId("commentaar");
    const inputUren = byId("uren");
    verbergPrestatieEnFouten();
    byId("toevoegen").onclick = async function (){
        if (!inputCommentaar.checkValidity()){
            toon("commentaarFout");
            inputCommentaar.focus();
        }
        if (!inputUren.checkValidity()){
            toon("urenFout");
            inputUren.focus();
        }
        const goedkeuring = {
            commentaar : inputCommentaar.value,
            uren : inputUren.value
        }
        await voegToe(goedkeuring);
    }

    function verbergPrestatieEnFouten (){
        verberg("prestatieTable");
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