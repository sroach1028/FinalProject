import { User } from './user';
import { Job } from './job';
import { Booking } from './booking';
export class Bid {
  id: number;
  available: boolean;
  bidAmount: number;
  description: string;
  accepted: boolean;
  rejected: boolean;
  job: Job;
  bidder: User;
  bookings: Booking[];

  constructor(
    id?: number,
    available?: boolean,
    bidAmount?: number,
    accepted?: boolean,
    rejected?: boolean,
    description?: string,
    job?: Job,
    bidder?: User,
    bookings?: Booking[]
  ) {
    this.id = id;
    this.available = available;
    this.bidAmount = bidAmount;
    this.description = description;
    this.accepted = false;
    this.rejected = false;
    this.bidder = bidder;
    this.job = job;
    this.bookings = bookings;
  }
}
