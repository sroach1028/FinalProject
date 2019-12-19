export class SkillMessage {
  id: number;
  message: string;
  messageDate: Date;
  inReplyTo: number;
  constructor(
    id: number,
    message: string,
    messageDate: Date,
    inReplyTo: number
  ) {
    this.id = id;
    this.message = message;
    this.messageDate = messageDate;
    this.inReplyTo = inReplyTo;
  }
}
