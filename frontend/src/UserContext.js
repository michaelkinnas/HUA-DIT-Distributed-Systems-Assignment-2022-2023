import { createContext } from "react";

export const UserContext = createContext(null);

// export function UserProvider({ children }) {
//     return (
//         <UserContext.Provider value={{ testValue: "test" }}>
//             {children}
//         </UserContext.Provider>
//     )
// }
