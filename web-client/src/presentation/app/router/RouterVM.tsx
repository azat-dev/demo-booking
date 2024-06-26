import {createBrowserRouter, useLoaderData} from "react-router-dom";
import RouteItem from "./RouteItem";
import AppSession from "../../../domain/auth/entities/AppSession.ts";
import React from "react";

const RouteComponent = React.memo(() => {

    return  useLoaderData() as any;
});

RouteComponent.displayName = "RouteComponent";

class RouterVM {

    public reactRouter: any;

    constructor(routes: RouteItem[], appSession: AppSession) {

        const mappedRoutes = routes.map((route) => {
            return {
                path: route.path,
                loader: async (params: any) => {
                    return await route.view(appSession.state, params);
                },
                element: <RouteComponent/>,
            };
        });

        this.reactRouter = createBrowserRouter(mappedRoutes);
    }

    public navigate = (path: string, replace: boolean = false): void => {
        this.reactRouter.navigate(path, {replace});
    }
}

export default RouterVM;