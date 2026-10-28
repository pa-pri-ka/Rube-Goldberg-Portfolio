import {Button, Col, Container, Form, Row} from "react-bootstrap";

export function SayHiContainer() {

	const sendHi = () => {

	};

	return <Container fluid>
			<Row className={'mt-3'}>
				<Col>
					<div className={'rounded-4 p-2 mx-0'}>
						<Row>
							<Col>
								<Form>
									<Form.Group controlId={'sayHiForm'}>
										<Form.Label>Say hello to <strong>Spongepost</strong>:</Form.Label><br/>
										<div className={'d-grid'}>
											<Button className={'rounded-4'} variant="primary" onClick={sendHi}>Hi Spongepost !</Button>
										</div>
										<Form.Text className="text-muted">
											He's a pirate, y'know...
										</Form.Text>
									</Form.Group>
								</Form>
							</Col>
							<Col>
								<Form>
									<Form.Label>Spongepost's responses:</Form.Label>
									<Form.Group controlId={'sayHiResponse'}>
										<Form.Control as={'textarea'} />
									</Form.Group>
								</Form>
							</Col>
						</Row>

					</div>
				</Col>
			</Row>
		</Container>;
}
