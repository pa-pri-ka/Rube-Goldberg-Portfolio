import SwaggerUI from "swagger-ui-react"
import "swagger-ui-react/swagger-ui.css"
import {RGP_SERVER_URL} from "~/env";
import axios from "axios";
import {useEffect, useState} from "react";
import {Col, Container, Row, Tab, Tabs} from "react-bootstrap";
import {AXIOS} from "~/config";
import {HeaderContainer} from "~/layout/HeaderContainer";
import {FooterContainer} from "~/layout/FooterContainer";

// noinspection JSUnusedGlobalSymbols
export default function OpenAPI() {

	const [openApiDocument, setOpenApiDocument] = useState<string | null>();
	const [generatedCodeDocuments, setGeneratedCodeDocuments] = useState<object | null>();
	const [springdocOas, setSpringdocOas] = useState<object | null>();

	useEffect(() => {
		const openApiDocumentsPromise: Promise<string | null> = axios.get(`${RGP_SERVER_URL}/openapi.yaml`, {
			responseType: 'text',
			timeout: AXIOS.TIMEOUT,
		}).then((response) => {
			return response.status === 200 ? response.data : null;
		});
		const generatedCodeDocumentsPromise: Promise<object | null> = axios.get(`${RGP_SERVER_URL}/api/openapi/generate`, {
			responseType: "json",
			timeout: AXIOS.TIMEOUT,
		}).then((response) => {
			return response.status === 200 ? response.data.files : null;
		});
		Promise.all([
			openApiDocumentsPromise,
			generatedCodeDocumentsPromise,
		]).then(([openApiDocuments, generatedCodeDocuments]) => {
			setOpenApiDocument(openApiDocuments);
			setGeneratedCodeDocuments(generatedCodeDocuments);
		});

		axios.get(`${RGP_SERVER_URL}/v3/api-docs`).then(response => {
			// noinspection MagicNumberJS
			setSpringdocOas(response.status === 200 ? response.data : null);
		});
	}, []);


	const renderCodeGeneratedByOpenApi = () => {
		const filePaths = Object.keys(generatedCodeDocuments as object);
		return filePaths.map((filePath) => {
			const parts = filePath.split(/[\/\\]/g);
			const filename = parts[parts.length - 1];
			// @ts-ignore
			const code = generatedCodeDocuments[filePath];
			return <Container fluid className={'m-1'} key={`${filename}ContainerKey`}>
				<Row key={`${filename}RowKey`}>
					<Col key={`${filename}ColKey`}>
						<h6>{filename}</h6>
						<code>
							<pre>{code}</pre>
						</code>
						<hr/>
					</Col>
				</Row>
			</Container>;
		});
	};

	return <>
		<HeaderContainer/>
		<hr/>
		<Tabs defaultActiveKey={'reactSwaggerUiEventKey'} fill={false} justify={false}
					transition={true} variant={'underline'}>
			<Tab eventKey={'reactSwaggerUiEventKey'} title={'React Swagger UI'}>
				<p className={'mx-3 my-3'}>React Swagger UI can be used to render any OAD document passed to it. In this
					example, it is rendering the existing <code>openapi.yaml</code> obtained from the back end.</p>
				{openApiDocument
					? <SwaggerUI spec={openApiDocument}/>
					: <p className={'mx-3 my-3'}>Fire in the hole...</p>}
			</Tab>
			<Tab eventKey={'openApiGeneratorEventKey'} title={'OpenAPI Generator'}>
				<p className={'mx-3 my-3'}>Using the <code>openapi-generator</code>, the back end can generate code from an
					existing OAD. My version even does that "in-memory", directly returning the generated documents as an API
					response. In this example, the generator has rendered the documents below from the existing
					<code>openapi.yaml</code> OAD.</p>
				{generatedCodeDocuments
					? renderCodeGeneratedByOpenApi()
					: <p className={'mx-3 my-3'}>All hands ohay...</p>}
			</Tab>
			<Tab eventKey={'springdocOasEventKey'} title={'Springdoc-generated OAS'}>
				<p className={'mx-3 my-3'}><code>springdoc</code>, used on the server side, can generate an OAD based on the API
					currently used by the API server:</p>
				{springdocOas ? <Container fluid className={'m-1'} key={'springdocOas'}>
						<Row>
							<Col>
								<code>
									<pre>{JSON.stringify(springdocOas, null, 2)}</pre>
								</code>
								<hr/>
							</Col>
						</Row>
					</Container>
					: <p className={'mx-3 my-3'}>Fire in the hole...</p>}
			</Tab>
			<Tab eventKey={'springdocsSwaggerUiEventKey'} title={'Springdocs Swagger'}>
				<p className={'mx-3 my-3'}><code>springdoc</code> can also expose a Swagger UI to document the currently-running
					APIs:</p>
				<iframe src={`http://localhost:8080/swagger-ui.html`} style={{width: '100%', height: '800px'}}></iframe>
			</Tab>
		</Tabs>
		<hr/>
		<FooterContainer/>
	</>;
}
