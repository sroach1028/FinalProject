import { Job } from './job';

export class Booking {
  id: number;
  startDate: Date;
  completeDate: Date;
  expectedCompleteDate: Date;
  notes: string;
  accepted: boolean;
  job: Job;
  sellerId: number;
  constructor(
    sellerId?: number,
    job?: Job,
    startDate?: Date,
    accepted?: boolean,
    completeDate?: Date,
    expectedCompleteDate?: Date,
    id?: number,
    notes?: string
  ) {
    this.id = id;
    this.startDate = startDate;
    this.completeDate = completeDate;
    this.expectedCompleteDate = expectedCompleteDate;
    this.notes = notes;
    this.accepted = accepted;
    this.sellerId = sellerId;
  }
}
