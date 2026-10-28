import type { Route } from "./+types/home";
import { Welcome } from "~/welcome/welcome";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "Spongepost Hibejab" },
    { name: "description", content: "PostgreSQL Spring Boot Hibernate JPA" },
  ];
}

export default function Home() {
  return <Welcome />;
}
