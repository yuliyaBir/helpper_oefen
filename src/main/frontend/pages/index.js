// import "../styles/global.css"
import React, { useState } from 'react';
export default function Home(props) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [emailError, setEmailError] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const [loginError, setLoginError] = useState('');

    const handleLogin = async () => {
        // Eenvoudige validatie voor de email en het wachtwoord
        if (!email) {
            setEmailError(true);
        } else {
            setEmailError(false);
        }

        if (!password) {
            setPasswordError(true);
        } else {
            setPasswordError(false);
        }

        // Als beide velden zijn ingevuld, voer dan de inlogactie uit
        if (email && password) {
            try {
                console.log(password);
                // Simuleer een API-aanroep (vervang dit met je eigen API-aanroep)
                const response = await fetch('http://localhost:8080/api/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ "email": email, "wachtwoord": password }),
                });

                const data = await response.json();

                if (response.ok) {
                    // Succesvol ingelogd, voer hier de verdere acties uit, bijvoorbeeld het bijwerken van de gebruikersstatus
                    console.log('Inloggen gelukt:', data);
                } else {
                    // Toon een foutmelding als de inlogpoging mislukt
                    setLoginError('Ongeldige inloggegevens');
                }
            } catch (error) {
                console.error('Fout bij inloggen:', error);
                setLoginError('Er is een fout opgetreden bij het inloggen');
            }
        }
    };

    return (
        <div>
            <h1>Welcome to Helpper.</h1>
            <p>Dat is een aplicatie voor assistenten en budgethouders.</p>

            <label>
                <span id="emailFout" className="fout" hidden={!emailError}>Verplicht in te vullen</span>
                <input   id="email"
                         type="email"
                         value={email}
                         onChange={(e) => setEmail(e.target.value)}
                         required
                         autoFocus></input>
            </label>
            <label>
                <span id="wachtwoordFout" className="fout" hidden={!passwordError}>Verplicht in te vullen</span>
                <input id="wachtwoord"
                       type="password"
                       value={password}
                       onChange={(e) => setPassword(e.target.value)}
                       required></input>
            </label>
            <button id="button" onClick={(e) => handleLogin()}>Inloggen</button>
        </div>
    )
}