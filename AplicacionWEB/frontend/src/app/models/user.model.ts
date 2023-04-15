export interface User {
    id?: number;
    email: string;
    username: string;
    encodedPassword: string;
    rol: string;
    favoritos: Event[];
    validated: boolean;
    checkToken: string;
  }
 
