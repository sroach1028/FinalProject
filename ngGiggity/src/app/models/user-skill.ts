import { Skill } from './skill';

export class UserSkill {
  id: number;
  description: string;
  name: string;
  skill: Skill;
  constructor(
    id: number,
    description?: string,
    skill?: Skill
  ) {
    this.id = id;
    this.description = description;
    this.skill = skill;
  }
}
