
export interface Comment {
  id?: number;
  event: Event;
  comment_user: string;
  description: string;
  time: string;
  //favorites: Set<User>;
}
