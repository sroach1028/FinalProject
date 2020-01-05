import { SellerReview } from './seller-review';
import { Bid } from './bid';
import { Job } from './job';

export class Booking {
  id: number;
  startDate: Date;
  completeDate: Date;
  expectedCompleteDate: Date;
  notes: string;
  accepted: boolean;
  job: Job;
  bid: Bid;
  sellerReview: SellerReview;
  constructor(
    bid?: Bid,
    job?: Job,
    startDate?: Date,
    accepted?: boolean,
    completeDate?: Date,
    expectedCompleteDate?: Date,
    id?: number,
    notes?: string,
    sellerReviews?: SellerReview

  ) {
    this.id = id;
    this.startDate = startDate;
    this.completeDate = completeDate;
    this.expectedCompleteDate = expectedCompleteDate;
    this.notes = notes;
    this.accepted = accepted;
    this.bid = bid;
    this.job = job;
    this.sellerReview = sellerReviews;
  }
}
