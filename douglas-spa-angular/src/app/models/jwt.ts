import { Authority } from "./authority";

export interface Jwt {
    token: string;
    bearer: string;
    userName: string;
    roles: Array<Authority>;
}
