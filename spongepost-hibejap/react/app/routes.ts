import {index, route, type RouteConfig} from "@react-router/dev/routes";

export default [
	index("routes/Home.tsx"),
	route("openapi", "routes/OpenAPI.tsx")
] satisfies RouteConfig;
