import { createContext } from "react";

export const UserContext = createContext();

export function UserProvider({ children }) {
    return (
        <UserContext.Provider value={{ testValue: "test" }}>
            {children}
        </UserContext.Provider>
    )
}
