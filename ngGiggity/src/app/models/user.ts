export class User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  email?: string;
  enabled: boolean;
  role: string;
  phone: string;
  constructor(
    id?: number,
    firstName?: string,
    lastName?: string,
    username?: string,
    password?: string,
    email?: string,
    enabled: boolean = true,
    role: string = 'standard',
    phone?: string,
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

  }
}
