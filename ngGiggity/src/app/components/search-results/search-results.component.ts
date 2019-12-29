import { BidService } from './../../services/bid.service';
import { BookingService } from './../../services/booking.service';
import { UserService } from './../../services/user.service';
import { Component, Input, ViewChild, NgZone, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { Job } from 'src/app/models/job';
import { Skill } from 'src/app/models/skill';
import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';
import { User } from 'src/app/models/user';
import { Booking } from 'src/app/models/booking';
import { Bid } from 'src/app/models/bid';
import { Form, NgForm } from '@angular/forms';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { MapsAPILoader, AgmMap } from '@agm/core';
import { GoogleMapsAPIWrapper } from '@agm/core';

//MAP FIELD
declare var google: any;

//MAP INTERFACES
interface Marker {
  lat: number;
  lng: number;
  label?: string;
  draggable: boolean;
}

interface Location {
  lat: number;
  lng: number;
  viewport?: Object;
  zoom: number;
  address_level_1?:string;
  address_level_2?: string;
  address_country?: string;
  address_zip?: string;
  address_state?: string;
  marker?: Marker;
}


@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  // MAP FIELDS
  geocoder: any;
  public location: Location = {
    lat: 39.7392,
    lng: -104.9903,
    marker: {
      lat: 39.7392,
      lng: -104.9903,
      draggable: true
    },
    zoom: 4
  };
  @ViewChild(AgmMap, {static: false}) map: AgmMap;
  // END MAP FIELDS

  // CLASS FIELDS
  jobs: Job[];
  jobTitle: string = null;
  title = 'Available Jobs';
  urlId: number;
  selected: Job = null;
  jobSkill: Skill = null;
  jobSkillName = null;
  jobCity = null;
  jobAddress: Address = new Address();
  booking: Booking = new Booking();
  bid: Bid = new Bid();
  newBid = false;
  user: User;
  updateGig: Job = null;
  skills: Skill[];
  users: User[];
  beginSearch = true;
  username = null;

  // C O N S T R U C T O R
  constructor(
    private jobSvc: JobService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private skillSvc: SkillService,
    private usersvc: UserService,
    private bookingsvc: BookingService,
    private bidSvc: BidService,
    public mapsApiLoader: MapsAPILoader,
    private zone: NgZone,
    private wrapper: GoogleMapsAPIWrapper
  ) {
    this.mapsApiLoader = mapsApiLoader;
    this.zone = zone;
    this.wrapper = wrapper;
    this.mapsApiLoader.load().then(() => {
    this.geocoder = new google.maps.Geocoder();
    });
  }

  ngOnInit() {
    this.getLoggedUser();
    this.location.marker.draggable = true;
    if (this.currentRoute.snapshot.paramMap.get('skillName')) {
      this.jobSkillName = this.currentRoute.snapshot.paramMap.get('skillName');
      this.jobBySkillName();
    }
  }

  // M A P    M E T H O D S
  updateOnMap() {
    // tslint:disable-next-line: variable-name
    let full_address: string = this.location.address_level_1 || " "
    if (this.location.address_level_2) { full_address = full_address + ' ' + this.location.address_level_2; }
    if (this.location.address_state) { full_address = full_address + ' ' + this.location.address_state; }
    if (this.location.address_country) { full_address = full_address + ' ' + this.location.address_country; }
    console.log(full_address);
    this.findLocation(full_address);
  }

  findLocation(address) {
    if (!this.geocoder) {this.geocoder = new google.maps.Geocoder(); }
    this.geocoder.geocode({
      'address': address
    }, (results, status) => {
      console.log(results);

      if (status == google.maps.GeocoderStatus.OK) {
        for (var i = 0; i < results[0].address_components.length; i++) {
          let types = results[0].address_components[i].types

          if (types.indexOf('locality') != -1) {
            this.location.address_level_2 = results[0].address_components[i].long_name
          }
          if (types.indexOf('country') != -1) {
            this.location.address_country = results[0].address_components[i].long_name
          }
          if (types.indexOf('postal_code') != -1) {
            this.location.address_zip = results[0].address_components[i].long_name
          }
          if (types.indexOf('administrative_area_level_1') != -1) {
            this.location.address_state = results[0].address_components[i].long_name
          }
        }

        if (results[0].geometry.location) {
          this.location.lat = results[0].geometry.location.lat();
          this.location.lng = results[0].geometry.location.lng();
          this.location.marker.lat = results[0].geometry.location.lat();
          this.location.marker.lng = results[0].geometry.location.lng();
          this.location.marker.draggable = true;
          this.location.viewport = results[0].geometry.viewport;
        }
        this.map.triggerResize();

      } else {
        alert("Sorry, this search produced no results.");
      }
    })
  }
  // E N D    MAP METHODS

  // M E T H O D S
  // onChange(deviceValue) {
  //   this.skills = deviceValue;
  // }
  checkSkill(job: Job, keyword: string) {
    return job.skill.name === keyword;
  }

  showUsers(user: User[]) {
    this.users = user;
  }

  getSkills() {
    this.skillSvc.index().subscribe(
      data => {
        this.skills = data;
      },
      err => {
        console.error('Update error in Compnent');
      }
    );
  }

  jobsByTitle() {
    this.jobSvc.findJobsByTitle(this.jobTitle).subscribe(
      data => {
        this.jobs = data;
      },
      err => {
        console.error('Job Title search error in Search-results component');
      }
    );
    this.beginSearch = false;
  }

  jobBySkillName() {
    this.jobSvc.findJobBySkill(this.jobSkillName).subscribe(
      aGoodThingHappened => {
        this.jobs = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
    this.beginSearch = false;
    this.jobSkillName = null;
  }

  jobByCity() {
    this.updateOnMap();
    this.jobSvc.findJobByCity(this.jobCity).subscribe(
      aGoodThingHappened => {
        this.jobs = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
    this.beginSearch = false;
  }

  jobByRemote() {
    this.jobSvc.findJobByRemote(true).subscribe(
      aGoodThingHappened => {
        this.jobs = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
    this.beginSearch = false;
  }

  backToSearch() {
    this.users = null;
    this.jobs = null;
    this.beginSearch = true;
  }

  searchByUsername() {
    this.usersvc.getUserSByUsername(this.username).subscribe(
      data => {
        this.users = data;
      },
      err => {
        console.error('Username search error in Search-results component');
      }
    );
    this.beginSearch = false;
  }

  displaySelected(job) {
    this.selected = job;
  }
  displayMessage(j) {
    return 'hello';
  }
  getSkill(id) {
    this.skillSvc.findByJobId(id).subscribe(data => {
      this.jobSkill = data;
    });
  }

  getAddress(id) {
    this.jobSvc.findJobAddress(id).subscribe(data => {
      this.jobAddress = data;
    });
  }

  getLoggedUser() {
    // return this.usersvc.getUserByUsername();
    this.usersvc.getUserByUsername().subscribe(
      data => {
        this.user = data;
      },
      err => console.error('Reload error in Component')
    );
  }

  createBookingBuyer() {
    this.booking.job = this.selected;
    this.bookingsvc.createBooking(this.booking).subscribe(
      data => {
        this.router.navigateByUrl('/user');
      },
      err => console.error('Create error in search-result-Component createBooking')
    );
  }

  createBid(form: NgForm) {

    this.bid = form.value;
    this.bidSvc.createBid(this.bid, this.selected.id).subscribe(
      data => {

      },
      err => console.error('Create error in search-result-Component createBid')
    );
  }

}
