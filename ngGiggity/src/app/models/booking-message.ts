export class BookingMessage {
  id: number;
  message: string;
  messageDate: Date;
  sellerid: number;
  constructor(
    id: number,
    message: string,
    messageDate: Date,
    sellerid: number
  ) {
    this.id = id;
    this.message = message;
    this.messageDate = messageDate;
    this.sellerid = sellerid;
  }
}
