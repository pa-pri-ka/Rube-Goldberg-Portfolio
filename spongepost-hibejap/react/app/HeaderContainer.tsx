import {Col, Container, Row} from "react-bootstrap";

export function HeaderContainer() {
	return <Container fluid>
		<Row>
			<Col>
				<div className={'border rounded-4 border-primary p-2 mx-0 my-2'}>
					<h1 className={'text-center'}>Spongepost Hibejab</h1>
					<h6 className={'text-center'}>A Rube Goldberg Portofolio's folly</h6>
				</div>
			</Col>
		</Row>
	</Container>
}
