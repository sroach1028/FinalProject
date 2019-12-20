import { Skill } from './skill';
import { Address } from './address';

export class Job {
  id: number;
  price: number;
  description: string;
  title: string;
  active: boolean;
  remote: boolean;
  imageUrl: string;
  dateCreated: Date;
  dateupdated: Date;
  skill: Skill;
  address: Address;
  constructor(
    id: number,
    price: number,
    description: string,
    title: string,
    active: boolean = true,
    remote: boolean,
    dateCreated: Date,
    dateupdated: Date,
    imageUrl?: string
  ) {
    this.id = id;
    this.price = price;
    this.description = description;
    this.title = title;
    this.active = active;
    this.dateCreated = dateCreated;
    this.dateupdated = dateupdated;
    this.imageUrl = imageUrl;
    this.remote = remote;
  }
}
