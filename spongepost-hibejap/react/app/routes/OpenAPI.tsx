import SwaggerUI from "swagger-ui-react"
import "swagger-ui-react/swagger-ui.css"
import {RGP_SERVER_URL} from "~/env";
import axios from "axios";
import {useState} from "react";
import {Col, Container, Row, Tab, Tabs} from "react-bootstrap";
import {AXIOS} from "~/config";

let dataLoadingState: undefined | 'ongoing' | 'done' = undefined;

// noinspection JSUnusedGlobalSymbols
export default function OpenAPI() {

	const [openApiDocument, setOpenApiDocument] = useState<string | null>();
	const [generatedCodeDocuments, setGeneratedCodeDocuments] = useState<object | null>();

	if (!dataLoadingState && dataLoadingState !== 'ongoing') {
		dataLoadingState = 'ongoing';
		const openApiDocumentsPromise: Promise<string | null> = axios.get(`${RGP_SERVER_URL}/openapi.yaml`, {
			responseType: 'text',
			timeout: AXIOS.TIMEOUT,
		}).then((response) => {
			return response.status === 200 ? response.data : null;
		});
		const generatedCodeDocumentsPromise : Promise<object | null>= axios.get(`${RGP_SERVER_URL}/api/openapi/generate`, {
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
		}).finally(() => {
			dataLoadingState = 'done';
		})
	}

	const renderGenerated = () => {
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

	return <Tabs defaultActiveKey={'reactSwaggerUiEventKey'} fill={true} id={'openApiTabsId'} justify={true}
							 transition={true} variant={'tabs'}>
		<Tab eventKey={'reactSwaggerUiEventKey'} title={'React Swagger UI'}>
			<p className={'mx-3 my-3'}>React Swagger UI can be used to render any OAD document passed to it. In this example,
				it is rendering the existing <code>openapi.yaml</code> obtained from the back end.</p>
			{openApiDocument
				? <SwaggerUI spec={openApiDocument}/>
				: <p className={'mx-3 my-3'}>Fire in the hole...</p>}
		</Tab>
		<Tab eventKey={'openApiGeneratorEventKey'} title={'OpenAPI Generator'}>
			<p className={'mx-3 my-3'}>Using the <code>openapi-generator</code>, the back end can generate code from an
				existing OAD. My version even does that "in-memory", directly returning the generated documents as an API
				response. In this example, the generator has rendered the documents below from the
				existing <code>openapi.yaml</code> OAD.</p>
			{generatedCodeDocuments
				? renderGenerated()
				: <p className={'mx-3 my-3'}>All hands ohay...</p>}
		</Tab>
		{/*<Tab eventKey={'springdocsEventKey'} title={'springdocs'}></Tab>*/}
	</Tabs>;
}
