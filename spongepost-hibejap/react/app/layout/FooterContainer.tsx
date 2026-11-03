import {Col, Container, Row} from "react-bootstrap";

export function FooterContainer() {
	return <Container fluid>
		<Row>
			<Col>
				<div className="text-center mt-2">
					Wanna talk pirate? Follow <a href={'https://www.yourdictionary.com/articles/pirate-terms'}
																			 type={'application/xhtml+xml'}
																			 target={'_blank'}>this link!</a>
				</div>
			</Col>
		</Row>
	</Container>
}
