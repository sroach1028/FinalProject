import { Skill } from './skill';
import { User } from './user';

export class UserSkill {
  id: number;
  description: string;
  name: string;
  skill: Skill;
  user: User;
  constructor(
    id?: number,
    description?: string,
    skill?: Skill,
    user?: User
  ) {
    this.id = id;
    this.description = description;
    this.skill = skill;
    this.user = user;
  }
}
