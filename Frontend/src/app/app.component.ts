import { Component, OnInit } from '@angular/core';
import { MDCTopAppBar } from '@material/top-app-bar';
import { MDCDrawer } from '@material/drawer';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'cities-exercise-frontend';
  // Links for anchors in the menu
  links = [
    { name: 'Home', link: '/' },
    { name: 'Cities', link: '/cities' },
  ];

  // Path to know which anchor is activated
  path;

  constructor(location: Location, router: Router) {
    router.events.subscribe((val) => {
      this.path = location.path() !== '' ? location.path() : '/';
    });
  }

  ngOnInit(): void {
    const topAppBar = MDCTopAppBar.attachTo(document.getElementById('app-bar'));
    const drawer = MDCDrawer.attachTo(document.querySelector('.mdc-drawer'));

    topAppBar.setScrollTarget(document.getElementById('main-content'));
    topAppBar.listen('MDCTopAppBar:nav', () => {
      drawer.open = !drawer.open;
    });
  }
}
