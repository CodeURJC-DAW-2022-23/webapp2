export interface User {
    id?: number;
    email: string;
    username: string;
    rol: string;
    favoritos: Event[];
    validated: boolean;
    checkToken: string;
  }