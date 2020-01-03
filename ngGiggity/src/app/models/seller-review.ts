import { Booking } from './booking';
export class SellerReview {
  id: number;
  comment: string;
  rating: number;
  reviewDate: Date;
  booking: Booking;
  constructor(
    id?: number,
    rating?: number,
    reviewDate?: Date,
    comment?: string,
    booking?: Booking
  ) {
    this.id = id;
    this.comment = comment;
    this.rating = rating;
    this.reviewDate = reviewDate;
    this.booking = booking;
  }
}
