import { Event } from "./event.model";
export interface Comment {
  id: number;
  event: Event;
  comment_user: string;
  description: string;
  time: string;
  totalLikes:number;
}
