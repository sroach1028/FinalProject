import { User } from './user';
import { Job } from './job';
export class Bid {
  id: number;
  available: boolean;
  bidAmount: number;
  description: string;
  accepted: boolean;
  rejected: boolean;
  job: Job;
  bidder: User;

  constructor(
    id?: number,
    available?: boolean,
    bidAmount?: number,
    accepted?: boolean,
    rejected?: boolean,
    description?: string,
    job?: Job,
    bidder?: User
  ) {
    this.id = id;
    this.available = available;
    this.bidAmount = bidAmount;
    this.description = description;
    this.accepted = accepted;
    this.rejected = rejected;
    this.bidder = bidder;
    this.job = job;
  }
}
