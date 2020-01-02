import { SkillService } from "./../../services/skill.service";
import { Address } from "./../../models/address";
import { Skill } from "src/app/models/skill";
import { JobService } from "src/app/services/job.service";
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Job } from "src/app/models/job";
import { NgForm } from "@angular/forms";
import { Router, NavigationEnd } from "@angular/router";

@Component({
  selector: "app-post",
  templateUrl: "./post.component.html",
  styleUrls: ["./post.component.css"]
})
export class PostComponent implements OnInit, OnDestroy {
  skills: Skill[] = [];
  skillSel: string;
  // newJob: Job = new Job();
  newAddy: Address = new Address();
  navigationSubscription;

  constructor(
    private jobSvc: JobService,
    private skillSvc: SkillService,
    private router: Router
  ) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.skillSel = null;
        this.newAddy = null;
        this.ngOnInit();
      }
    });
  }

  reload() {
    this.skillSvc.index().subscribe(
      aGoodThingHappened => {
        this.skills = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
  }
  ngOnInit() {
    this.reload();
  }

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  postJob(form: NgForm) {
    const newJob = form.value;
    newJob.skill = new Skill(newJob.skillId);
    delete newJob.skillId;

    newJob.active = true;
    this.jobSvc.postJob(newJob).subscribe(
      data => {
        this.router.navigateByUrl("/user");
      },
      err => console.error("Reload error in Component")
    );
  }
}
