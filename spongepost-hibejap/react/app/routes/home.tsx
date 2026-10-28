import type {Route} from "./+types/home";
import {HeaderContainer} from "~/HeaderContainer";
import {TechStackContainer} from "~/TechStackContainer";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "Spongepost Hibejab" },
    { name: "description", content: "PostgreSQL Spring Boot Hibernate JPA" },
  ];
}

export default function Home() {
  return <>
    <HeaderContainer />
    <TechStackContainer />
  </>
}
