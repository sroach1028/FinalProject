import { Address } from './address';
import { Image } from './image';
import { Job } from './job';
import { UserSkill } from './user-skill';
import { Bid } from './bid';
export class User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email?: string;
  password: string;
  enabled: boolean;
  role: string;
  phone: string;
  address: Address;
  avatarImage: Image;
  skills: UserSkill[];
  jobs: Job[];
  bio: string;
  bids: Bid[];
  constructor(
    id?: number,
    firstName?: string,
    lastName?: string,
    username?: string,
    password?: string,
    email?: string,
    enabled: boolean = true,
    role: string = 'standard',
    address?: Address,
    phone?: string,
    avatarImage?: Image,
    jobs?: Job[],
    bio?: string,
    bids?: Bid[]
    ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.enabled = enabled;
    this.role = role;
    this.phone = phone;
    this.address = address;
    this.avatarImage = avatarImage;
    this.jobs = jobs;
    this.bio = bio;
    this.bids = bids;

  }
}
