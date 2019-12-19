import { Image } from './image';
import { Address } from './address';
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
    avatarImage?: Image
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

  }
}
