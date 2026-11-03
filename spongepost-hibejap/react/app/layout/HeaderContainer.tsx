import {Button, Col, Container, Row} from "react-bootstrap";
import {useNavigate} from "react-router";
import {House} from "react-bootstrap-icons";
import '../common.css'

export function HeaderContainer() {

	const navigate = useNavigate();

	const homeClicked = () => {
		navigate("/");
	};

	return <Container fluid>
		<Row>
			<Col className={'position-relative'}>
				<div className={'border rounded-4 border-primary p-2 mx-0 my-2'}>
					<h1 className={'text-center'}>Spongepost Hibejab</h1>
					<h6 className={'text-center'}>A Rube Goldberg Portofolio's folly</h6>
				</div>
				<Button className={'position-absolute rounded-4 top-0 start-0 link-icon-light mt-1 ms-1'} size={'lg'} onClick={homeClicked} variant={'link'}>
					<House size={'1.5em'} />
				</Button>
			</Col>
		</Row>
	</Container>
}

