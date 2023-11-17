export default function Budgethouder(props) {
    return (
        <>
            <h2>Welcome to jouw pagina!</h2>
            <h3>Hier kan je prestaties vinden.</h3>
            <div id={"aantalNietGoedgekeurdePrestaties"}>Nu heb je {props.aantalPrestatiesZonderGoedkeuring} prestaties om goed te keuren.</div>
            <div id={"goedgekeurdePrestaties"}>Hier kan je jouw goedgekeurde prestaties terugvinden.</div>
        </>
    )
}

export async function getStaticProps() {
    const response = await fetch("http://localhost:8080/prestaties/aantalPrestatiesZonderGoedkeuring")
    const data = JSON.stringify(response.json());

    return {
        props: {
            aantalPrestatiesZonderGoedkeuring: data
        },
        revalidate: 10
    }
}