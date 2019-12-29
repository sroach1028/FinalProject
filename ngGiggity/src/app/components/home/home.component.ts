import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';
import { Component, Input, ViewChild, NgZone, OnInit } from '@angular/core';
import { MapsAPILoader, AgmMap } from '@agm/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
import { Skill } from 'src/app/models/skill';

// MAP STUFF
declare var google: any;

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

// END MAP STUFF

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  geocoder: any;
  public location: Location = {
    lat: 39.7392,
    lng: -104.9903,
    marker: {
      lat: 39.7392,
      lng: -104.9903,
      draggable: true
    },
    zoom: 5
  };

  @ViewChild(AgmMap, {static: false}) map: AgmMap;

skills: Skill[] = [];
totalskills: number;

reload() {
  this.skillSvc.index().subscribe(
    (aGoodThingHappened) => {
      this.skills = aGoodThingHappened;
      this.totalskills = this.skills.length;
    },
    (didntWork) => {
      console.error(didntWork);
    }
  );
}

  constructor(private skillSvc: SkillService, private jobSvc: JobService, public mapsApiLoader: MapsAPILoader,
    private zone: NgZone,
    private wrapper: GoogleMapsAPIWrapper) {
      this.mapsApiLoader = mapsApiLoader;
      this.zone = zone;
      this.wrapper = wrapper;
      this.mapsApiLoader.load().then(() => {
      this.geocoder = new google.maps.Geocoder();
      });
    }

  ngOnInit() {
    this.location.marker.draggable = true;
    this.reload();
  }

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

        this.map.triggerResize()
      } else {
        alert("Sorry, this search produced no results.");
      }
    })
  }

}
