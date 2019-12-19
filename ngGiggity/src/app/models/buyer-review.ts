export class BuyerReview {
  id: number;
  comment: string;
  rating: number;
  reviewDate: Date;
  constructor(
    id: number,
    rating: number,
    reviewDate: Date,
    comment?: string
  ) {
    this.id = id;
    this.comment = comment;
    this.rating = rating;
    this.reviewDate = reviewDate;
  }
}
