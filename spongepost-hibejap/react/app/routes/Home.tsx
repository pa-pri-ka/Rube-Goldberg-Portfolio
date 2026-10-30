import type {Route} from "./+types/Home";
import {HeaderContainer} from "~/layout/HeaderContainer";
import {TechStackContainer} from "~/layout/TechStackContainer";
import {SayHiContainer} from "~/say_hi/SayHiContainer";
import {FooterContainer} from "~/layout/FooterContainer";
import {DemosList} from "~/demos/DemosList";

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
    <hr/>
    <SayHiContainer />
    <hr/>
    <DemosList />
    <hr/>
    <FooterContainer />
  </>
}
