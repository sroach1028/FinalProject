import { Job } from './job';

export class Booking {
  id: number;
  startDate: Date;
  completeDate: Date;
  expectedCompleteDate: Date;
  notes: string;
  accepted: boolean;
  jobId: number;
  bidId: number;
  constructor(
    bidId?: number,
    jobId?: number,
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
    this.bidId = bidId ;
    this.jobId = jobId ;
  }
}
