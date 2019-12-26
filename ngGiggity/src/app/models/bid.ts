import { User } from './user';
export class Bid {
  id: number;
  available: boolean;
  bidAmount: number;
  description: string;
  accepted: boolean;
  bidder: User;

  constructor(
    id?: number,
    available?: boolean,
    bidAmount?: number,
    accepted?: boolean,
    description?: string,
    bidder?: User
  ) {
    this.id = id;
    this.available = available;
    this.bidAmount = bidAmount;
    this.description = description;
    this.accepted = accepted;
    this.bidder = bidder;
  }
}
