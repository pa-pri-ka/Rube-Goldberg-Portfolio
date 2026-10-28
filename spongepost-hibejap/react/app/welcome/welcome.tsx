import {Alert, AlertHeading, Col, Container, ListGroup, ListGroupItem, Row, Stack} from "react-bootstrap";

export function Welcome() {
	const itemClasses = 'rounded-pill rounded border-dark-subtle bg-dark text-primary p-2';

	return <>
		<Container fluid>
			<Row>
				<Col>
					<div className={'border rounded-4 border-primary p-2 mx-0 my-2'}>
					<h1 className={'text-center'}>Spongepost Hibejab</h1>
					<h6 className={'text-center'}>A Rube Goldberg Portofolio's folly</h6>
					</div>
				</Col>
			</Row>
			<Row className={'mt-4'}>
				<Col>
					<Alert variant={'secondary'}>
						<AlertHeading>Featuring</AlertHeading>
						<Stack direction={'horizontal'} gap={2} className={'flex-wrap'}>
							<div className={itemClasses}>Spring Boot</div>
							<div className={itemClasses}>Spring Boot Web</div>
							<div className={itemClasses}>Spring Boot Data JPA</div>
							<div className={itemClasses}>Bruce Springsteen</div>
							<div className={itemClasses}>Spring Boot Docker Compose</div>
							<div className={itemClasses}>Springdoc OpenAPI</div>
							<div className={itemClasses}>PostgreSQL</div>
							<div className={itemClasses}>Pete Postlethwaite</div>
							<div className={itemClasses}>React</div>
							<div className={itemClasses}>React Bootstrap</div>
							<div className={itemClasses}>React Router</div>
							<div className={itemClasses}>Stephen Rea</div>
						</Stack>
					</Alert>
				</Col>
			</Row>
		</Container>
	</>;
}
