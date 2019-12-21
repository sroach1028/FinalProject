import { Skill } from './skill';
import { Address } from './address';
import { User } from './user';


export class Job {
  id: number;
  price: number;
  description: string;
  title: string;
  active: boolean;
  activeremote: boolean;
  imageUrl: string;
  dateCreated: Date;
  dateupdated: Date;
  skill: Skill;
  remote: boolean;
  address: Address;
  user: User;
  constructor(
    id: number,
    price: number,
    description: string,
    title: string,
    active: boolean = true,
    remote: boolean,
    dateCreated: Date,
    dateupdated: Date,
    imageUrl?: string,
    skill?: Skill,
    address?: Address,
    user?: User
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
