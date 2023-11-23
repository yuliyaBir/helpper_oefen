"use strict"
import {byId, verberg, toon, setText, verwijderChildElementenVan} from "./util.js";
const knopToevoegen = byId("registreren");


console.log("rol");
knopToevoegen.onclick = async function () {
    const inputVoornaam = byId("voornaam");
    const inputFamilienaam = byId("familienaam");
    const inputEmail = byId("email");
    const inputWachtwoord = byId("wachtwoord");
    const inputRol = byId("rol");
    inputRol.onchange = function () {
        inputRol.value = this.value;
    };
    console.log(inputRol.value);
    verbergFouten();
    if (!inputVoornaam.checkValidity()) {
        toon("voornaamFout");
        inputVoornaam.focus();
        inputVoornaam.value = "";
        return;
    }
    if (!inputFamilienaam.checkValidity()) {
        toon("familienaamFout");
        inputFamilienaam.focus();
        inputFamilienaam.value = "";
        return;
    }
    if (!inputEmail.checkValidity()) {
        toon("emailFout");
        inputEmail.value = "";
        inputEmail.focus();
        return;
    }
    if (!inputWachtwoord.checkValidity()) {
        toon("wachtwoordFout");
        inputWachtwoord.focus();
        inputWachtwoord.value = "";
        return;
    }
    console.log(inputWachtwoord);
    const nieuweUser = {
            voornaam: inputVoornaam.value,
            familienaam: inputFamilienaam.value,
            email: inputEmail.value,
            wachtwoord: inputWachtwoord.value,
            rol: inputRol.value
    }
    console.log(nieuweUser);
    // await checkVoorAlBestaandeUser(inputEmail.value);
    await voegToe(nieuweUser);
}
async function voegToe(nieuweUser) {
    var response = await fetch("/api/users/nieuweUser",
        {
            method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(nieuweUser)
        });
    console.log(response);
    if (response.ok) {
        toon("geregestreerd");
    } else {
        if (response.status === 409) {
            setText("conflict", "Persoon met deze email is al bestaan");
            toon("conflict");
            inputVoornaam.value = "";
            inputVoornaam.focus();
            inputFamilienaam.value = "";
            inputEmail.value = "";
            inputWachtwoord.value = "";
        } else {
            toon("storing");
        }
    }
}
function verbergFouten (){
    verberg("voornaamFout");
    verberg("familienaamFout");
    verberg("emailFout");
    verberg("wachtwoordFout");
    verberg("storing");
    verberg("conflict");
    verberg("geregestreerd");
}