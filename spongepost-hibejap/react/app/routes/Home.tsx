import type {Route} from "./+types/home";
import {HeaderContainer} from "~/layout/HeaderContainer";
import {TechStackContainer} from "~/layout/TechStackContainer";
import {SayHiContainer} from "~/say_hi/SayHiContainer";

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
    <SayHiContainer />
  </>
}
