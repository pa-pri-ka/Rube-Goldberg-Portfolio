import {Col, Container, ListGroup, Row} from "react-bootstrap";
import {useNavigate} from "react-router";

export function DemosList() {

	const navigate = useNavigate();

	const itemClicked = () => {
		navigate("/openapi");
	};

	return <Container fluid>
		<Row className={'mt-4'}>
			<Col>
				<h6>Arr! Wanna see me <strong>booties</strong> ? Raise the <strong>black jack</strong>, me <strong>hearties!</strong></h6>
				<ListGroup className="mt-4">
					<ListGroup.Item action={true} onClick={itemClicked}>
						<p className={'text-primary'}>Let's have a look at me binnacle !</p>
						<span className={'text-light small'}>
							<strong>OpenAPI</strong>:
							<ul>
								<li>Endpoints documentation with <strong>springdoc</strong></li>
								<li>Endpoint generation using <strong>OpenAPI Generator</strong></li>
								<li>Visualization with <strong>React Swagger UI</strong></li>
							</ul>
						</span>
					</ListGroup.Item>
				</ListGroup>
			</Col>
		</Row>
	</Container>;
}
