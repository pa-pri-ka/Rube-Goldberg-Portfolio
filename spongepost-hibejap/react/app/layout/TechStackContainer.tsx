import {Alert, AlertHeading, Col, Container, Row, Stack} from "react-bootstrap";

export function TechStackContainer() {
	const itemClasses = 'rounded-pill border-dark-subtle bg-dark text-primary p-2';

	return <Container fluid>
		<Row className={'mt-4'}>
			<Col>
				<Alert variant={'secondary'}>
					<AlertHeading className={'small'}>Featuring</AlertHeading>
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
}
