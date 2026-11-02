import SwaggerUI from "swagger-ui-react"
import "swagger-ui-react/swagger-ui.css"
import {RGP_SERVER_URL} from "~/env";
import axios, {AxiosHeaders} from "axios";
import {useState} from "react";
import {Button} from "react-bootstrap";

export default function OpenAPI() {
	const [openApiDocument, setOpenApiDocument] = useState();
	const [type, setType] = useState(true);

	if (!openApiDocument) {
		axios.get("http://localhost:8080/openapi.yaml", {
			// responseType: "text",
		}).then((response) => {
			console.log(response);
			setOpenApiDocument(response.data);
		});
	}

	return <>
		{/*<Button onClick={() => setType(!type)}>Switch</Button>*/}
		{type && openApiDocument ?
			<SwaggerUI spec={openApiDocument}/>
			: <p>Fire in the hole...</p>}

		{!type && <SwaggerUI url="https://petstore.swagger.io/v2/swagger.json" />}
	</>;
}
