import { Skill } from 'src/app/models/skill';
import { Address } from './address';
import { User } from './user';


export class Job {
  id: number;
  price: number;
  description: string;
  title: string;
  active: boolean;
  imageUrl: string;
  dateCreated: Date;
  dateupdated: Date;
  skill: Skill;
  remote: boolean;
  address: Address;
  user: User;
  constructor(
    active: boolean = true,
    remote?: boolean,
    dateCreated?: Date,
    dateupdated?: Date,
    id?: number,
    price?: number,
    description?: string,
    title?: string,
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
    this.skill = skill;
    this.address = address;
  }
}
