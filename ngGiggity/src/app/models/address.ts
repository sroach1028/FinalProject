export class Address {
  id: number;
  city: string;
  state: string;
  zip: number;
  street: string;
  constructor(
    id?: number,
    city?: string,
    state?: string,
    zip?: number,
    street?: string
  ) {
    this.id = id;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.street = street;
  }
}
